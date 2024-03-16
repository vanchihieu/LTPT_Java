package dao;

import static com.mongodb.MongoClientSettings.getDefaultCodecRegistry;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.bson.Document;
import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.DeleteResult;

import db.Connection;
import entities.Customer;
import entities.Product;

public class CustomerDao {
	private MongoCollection<Customer> customerColl;
	private MongoCollection<Document> docColl;
	MongoDatabase db;
	
	public CustomerDao() {
		MongoClient client = Connection.getInstance().getMongoClient();
		db = client.getDatabase("BikeStores");

		CodecProvider pojoCodecProvider = PojoCodecProvider.builder().automatic(true).build();
		CodecRegistry pojoCodecRegistry = fromRegistries(getDefaultCodecRegistry(), fromProviders(pojoCodecProvider));

		customerColl = db.getCollection("customers", Customer.class).withCodecRegistry(pojoCodecRegistry);
		docColl = db.getCollection("customers");
	}
	
//	4.	Thống kê số khách hàng theo từng bang
	// db.customers.aggregate([{$group:{_id:'$address.state',total:{$sum:1}}}])
	// + getNumberCustomerByState() : Map<String, Integer>
	public Map<String, Integer> getNumberCustomerByState() {
		Map<String, Integer> map = new HashMap<String, Integer>();
		AggregateIterable<Document> output = docColl.aggregate(Arrays.asList(new Document("$group",
				new Document("_id", "$address.state").append("total", new Document("$sum", 1)))));
		for (Document dbObject : output) {
			map.put(dbObject.getString("_id"), dbObject.getInteger("total"));
		}
		return map;
	}
	
//	12.Xóa tất cả các khách hàng chưa mua hàng.
//	db.customers.find({_id:{$eq:db.orders.distince("customer.customer_id")}})
	public boolean deleteCustomerNotOrder() {
	 	DeleteResult rs = customerColl.deleteMany(Filters.nin("_id",
				db.getCollection("orders")
				.distinct("customer.customer_id",
						String.class)));
	 	return rs.getDeletedCount() > 0;
	}
	
//	13.Danh sách các khách hàng có từ 2 số điện thoại trở lên
//	db.customers.find({$expr:{$gt:[{$size:"$phones"}, 1]}})
	public ArrayList<Customer> getCustomerHave2Phone(){
		return  customerColl.find(Filters.and(
		        Filters.exists("phones"),
		        Filters.expr(Document.parse("{$gt: [{$size: '$phones'}, 1]}"))
		    ))
		    .into(new ArrayList<Customer>());
	}
	
//	15.Tìm khách hàng khi biết số điện thoại
//	db.customers.find({phones: {$elemMatch: {number: "(212) 945-8823"}}})
	public Customer findByPhone(String phone) {
		return customerColl.find(Filters.elemMatch("phones",Filters.eq("number", phone))).first();
	}
}
