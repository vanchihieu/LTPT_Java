package dao;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.reactivestreams.Publisher;

import com.mongodb.MongoClientSettings;
import com.mongodb.client.model.Accumulators;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.InsertOneResult;
import com.mongodb.reactivestreams.client.AggregatePublisher;
import com.mongodb.reactivestreams.client.DistinctPublisher;
import com.mongodb.reactivestreams.client.FindPublisher;
import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoCollection;
import com.mongodb.reactivestreams.client.MongoDatabase;

import db.Connection;
import entity.Customer;

public class CustomerDao {
	private MongoCollection<Customer> customerColl;
	private MongoCollection<Document> docColl;
	private MongoDatabase db;
	
	
	public CustomerDao() {
		MongoClient client = Connection.getInstance().getClient();
		db = client.getDatabase("BikeStores");
		CodecRegistry pojoCodecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),
				fromProviders(PojoCodecProvider.builder().automatic(true).build()));
		customerColl = db.getCollection("customers", Customer.class).withCodecRegistry(pojoCodecRegistry);
		docColl = db.getCollection("customers");
	}
	
	public boolean insertCustomer(Customer c) {
		Publisher<InsertOneResult> publisher = customerColl.insertOne(c);
		BikeSubscriber<InsertOneResult> sub = new BikeSubscriber<>();
		publisher.subscribe(sub);
		return sub.getSingleResult().getInsertedId() != null;
	}
	
	public Customer findCustomerById(String id) {
		FindPublisher<Customer> publisher = customerColl.find(Filters.eq("_id",id));
		BikeSubscriber<Customer> sub = new BikeSubscriber<>();
		publisher.subscribe(sub);
		return sub.getSingleResult();
	}
	
//	4.Thống kê số khách hàng theo từng bang.
//	db.customers.aggregate([{$group:{_id:'$address.state',total:{$sum:1}}}])
	public Map<String, Integer> getNumberCustomerByState(){
		Map<String, Integer> map = new HashMap<>();
		AggregatePublisher<Document> aggregate = docColl.aggregate(Arrays.asList(
				Aggregates.group("$address.state",Accumulators.sum("total", 1))
				));
		BikeSubscriber<Document> sub = new BikeSubscriber<Document>();
		aggregate.subscribe(sub);
		List<Document> results = sub.getResults();
		for (Document doc : results) {
			map.put(doc.getString("_id"),doc.getInteger("total"));
		}
		return map;
	}
//	 12.Xóa tất cả các khách hàng chưa mua hàng
//	db.customers.deleteMany({_id:{$nin:db.orders.distinct("customer.customer_id")})
	public boolean deleteCusNotOrder() {
		DistinctPublisher<String> distinct = db.getCollection("orders").distinct("customer.customer_id",String.class);
		BikeSubscriber<String> sub1 = new BikeSubscriber<>();
		distinct.subscribe(sub1);
		List<String> results = sub1.getResults();
		Publisher<DeleteResult> publisher = customerColl.deleteMany(Filters.nin("_id",results));
		BikeSubscriber<DeleteResult> sub = new BikeSubscriber<>();
		publisher.subscribe(sub);
		return sub.getSingleResult().getDeletedCount() > 0;
	}
//	13. Danh sách các khách hàng có từ 2 số điện thoại trở lên
//	db.customers.find({$and: [{phones: {$exists: true}}
//	, {$expr: {$gt: [{$size: "$phones"}, 1]}}]})
	public List<Customer> getCustomerPhoneGtTwo(){
		FindPublisher<Customer> publisher = customerColl.find(Filters.and(
				Filters.exists("phones",true),
				Filters.expr(Document.parse("{$gt: [{$size: \"$phones\"}, 1]}"))
				));
		BikeSubscriber<Customer> sub = new BikeSubscriber<Customer>();
		publisher.subscribe(sub);
		return sub.getResults();
	}
//	15. Tìm khách hàng khi biết số điện thoại
//	db.customers.find({phones: {$elemMatch: {number: "(212) 945-8823"}}})
	public Customer findCustomerByPhone(String phone) {
		FindPublisher<Customer> publisher = customerColl.find(Filters.elemMatch("phones",Filters.eq("number",phone)));
		BikeSubscriber<Customer> sub = new BikeSubscriber<>();
		publisher.subscribe(sub);
		return sub.getSingleResult();
	}

	

}
