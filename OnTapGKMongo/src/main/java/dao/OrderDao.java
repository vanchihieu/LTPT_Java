package dao;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

import java.time.Instant;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.types.ObjectId;
import org.reactivestreams.Publisher;

import com.mongodb.MongoClientSettings;
import com.mongodb.client.model.Accumulators;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Filters;
import com.mongodb.reactivestreams.client.AggregatePublisher;
import com.mongodb.reactivestreams.client.FindPublisher;
import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoCollection;
import com.mongodb.reactivestreams.client.MongoDatabase;

import db.Connection;
import entity.Customer;
import entity.Order;
import entity.Product;

public class OrderDao {
	private MongoCollection<Order> orderColl;
	private MongoCollection<Document> docColl;

	public OrderDao() {
		MongoClient client = Connection.getInstance().getClient();
		MongoDatabase db = client.getDatabase("BikeStores");
		CodecRegistry pojoCodecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),
				fromProviders(PojoCodecProvider.builder().automatic(true).build()));
		orderColl = db.getCollection("orders", Order.class).withCodecRegistry(pojoCodecRegistry);
		docColl = db.getCollection("orders");
	}

//	5. Tính tổng tiền của đơn hàng khi biết mã số đơn hàng.
	public double getTotalCusByOrderId(ObjectId id) {
		FindPublisher<Order> publisher = orderColl.find(Filters.eq("_id", id));
		BikeSubscriber<Order> sub = new BikeSubscriber<>();
		publisher.subscribe(sub);
		return sub.getSingleResult().getOrderTotal();
	}

//	6. Đếm số đơn hàng của từng khách hàng.
//	db.orders.aggregate([{$group:{_id:'$customer.customer_id',total:{$sum:1}}}])
	public Map<Customer, Integer> getOrdersByCustomers() {
		Map<Customer, Integer> map = new HashMap<>();
		AggregatePublisher<Document> aggregate = docColl
				.aggregate(Arrays.asList(Aggregates.group("$customer.customer_id", Accumulators.sum("total", 1))));
		BikeSubscriber<Document> sub1 = new BikeSubscriber<>();
		aggregate.subscribe(sub1);
		List<Document> docs = sub1.getResults();
		for (Document doc : docs) {
			CustomerDao customerDao = new CustomerDao();
			map.put(customerDao.findCustomerById(doc.getString("_id")), doc.getInteger("total"));
		}
		return map;
	}

//	7.Tính tổng số lượng của từng sản phẩm đã bán ra.
//	db.orders.aggregate([{$unwind:'$order_details'},
//	{$group:{_id:'order_details.product_id',total:{$sum:1}}}])
//	+ getTotalProduct(): Map<Product, Integer>
	public Map<Product, Integer> getTotalProduct() {
		Map<Product, Integer> map = new HashMap<>();
		AggregatePublisher<Document> aggregate = docColl.aggregate(Arrays.asList(Aggregates.unwind("$order_details"),
				Aggregates.group("$order_details.product_id", Accumulators.sum("total", 1))));
		BikeSubscriber<Document> sub = new BikeSubscriber<>();
		aggregate.subscribe(sub);
		List<Document> docs = sub.getResults();
		for (Document doc : docs) {
			long id = doc.getLong("_id");
			int total = doc.getInteger("total");
			ProductDao productDao = new ProductDao();
			Product product = productDao.findProductById(id);
			map.put(product, total);
		}
		return map;
	}

//	9.Tính tổng tiền của tất cả các hóa đơn trong một ngày nào đó.
	public double getOrderTotalByDate(Instant date) {
		double total = 0;
		Publisher<Order> first = orderColl.find(Filters.eq("order_date", date));
		BikeSubscriber<Order> sub = new BikeSubscriber<Order>();
		first.subscribe(sub);
		List<Order> list = sub.getResults();
		for (Order order : list) {
			total += order.getOrderTotal();
		}
		return total;
	}
	public double getOrderTotalByDate(Date date) {
		AggregatePublisher<Document> aggregate = docColl.aggregate(Arrays.asList(
				Aggregates.match(Filters.eq("order_date",date)),
				Aggregates.group("$order_date", Accumulators.sum("total", "$order_total"))));
		BikeSubscriber<Document> sub = new BikeSubscriber<>();
		aggregate.subscribe(sub);
		 Document docs = sub.getSingleResult();
		return docs.getDouble("total");
	}
	
	public List<Order> getOrderByDate(Date date){
		FindPublisher<Order> find = orderColl.find(Filters.eq("order_date",date));
		BikeSubscriber<Order> sub = new BikeSubscriber<>();
		find.subscribe(sub);
		return sub.getResults();
	}

//	14.Thống kê tổng tiền hóa đơn theo tháng / năm
//	db.orders.aggregate([
//	  {
//		    $group: {
//		      _id: {
//		        month: { $month: "$order_date" },
//		        year: { $year: "$order_date" }
//		      },
//		      total: { $sum: "$order_total" }
//		    }
//		  }
//		])

	public Map<String, Double> getOrderTotalByMonthAndYear() {
		Map<String, Double> map = new HashMap<>();
		AggregatePublisher<Document> aggregate = docColl.aggregate(Arrays.asList(
				Aggregates.group(Document.parse("{month:{$month:'$order_date'},year:{$year:'$order_date'}}"),
						Accumulators.sum("total", "$order_total"))));
		BikeSubscriber<Document> sub = new BikeSubscriber<>();
		aggregate.subscribe(sub);
		List<Document> results = sub.getResults();
		for (Document doc : results) {
			Document id = (Document) doc.get("_id");
//			System.out.println(id);
			int month = id.getInteger("month");
			int year = id.getInteger("year");
			double total = doc.getDouble("total");
			String date = String.valueOf(month) + "/" + String.valueOf(year);
			map.put(date, total);
		}
		return map;
	}

}
