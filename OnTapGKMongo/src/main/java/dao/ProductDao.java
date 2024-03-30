package dao;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.nin;
import static com.mongodb.client.model.Filters.text;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

import java.util.List;

import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.conversions.Bson;
import org.reactivestreams.Publisher;

import com.mongodb.MongoClientSettings;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.IndexOptions;
import com.mongodb.client.model.Indexes;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.InsertManyResult;
import com.mongodb.client.result.InsertOneResult;
import com.mongodb.client.result.UpdateResult;
import com.mongodb.reactivestreams.client.DistinctPublisher;
import com.mongodb.reactivestreams.client.FindPublisher;
import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoCollection;
import com.mongodb.reactivestreams.client.MongoDatabase;

import db.Connection;
import entity.Product;

public class ProductDao {
	private MongoCollection<Product> productColl;
	private MongoCollection<Document> docColl;
	private MongoCollection<Document> orderCollection;
	private MongoDatabase db;

	public ProductDao() {
		MongoClient client = Connection.getInstance().getClient();
		db = client.getDatabase("BikeStores");
		CodecRegistry pojoCodecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),
				fromProviders(PojoCodecProvider.builder().automatic(true).build()));
		productColl = db.getCollection("products", Product.class).withCodecRegistry(pojoCodecRegistry);
		docColl = db.getCollection("products");
	}

	public List<Product> getAllProduct() {
		FindPublisher<Product> find = productColl.find();
		BikeSubscriber<Product> sub = new BikeSubscriber<>();
		find.subscribe(sub);
		return sub.getResults();
	}

//	Insert a document
	public boolean insertDoc(Product p) {
		Publisher<InsertOneResult> insertOne = productColl.insertOne(p);
		BikeSubscriber<InsertOneResult> sub = new BikeSubscriber<InsertOneResult>();
		insertOne.subscribe(sub);
		return sub.getSingleResult().getInsertedId() != null;
	}

//	Insert Many
	public boolean insertManyDoc(List<Product> products) {
		Publisher<InsertManyResult> insertMany = productColl.insertMany(products);
		BikeSubscriber<InsertManyResult> sub = new BikeSubscriber<InsertManyResult>();
		insertMany.subscribe(sub);
		return sub.getSingleResult() != null;
	}

//	Delete One document
	public boolean deleteDoc(long id) {
		Publisher<DeleteResult> publisher = productColl.deleteOne(eq("_id", id));
		BikeSubscriber<DeleteResult> sub = new BikeSubscriber<DeleteResult>();
		publisher.subscribe(sub);
		return sub.getSingleResult().getDeletedCount() > 0;
	}

// Update a doc
	public boolean updateDoc(Product p) {
		Publisher<UpdateResult> publisher = productColl.updateOne(eq("_id", p.getProductId()), new Document("$set", p));
		BikeSubscriber<UpdateResult> sub = new BikeSubscriber<UpdateResult>();
		publisher.subscribe(sub);
		return sub.getSingleResult().getModifiedCount() > 0;
	}

//	Update Many doc
	public boolean updateManyDoc(String name) {
		Publisher<UpdateResult> publisher = productColl.updateMany(eq("product_name", name),
				new Document("$set", new Document("price", 2000)));
		BikeSubscriber<UpdateResult> sub = new BikeSubscriber<UpdateResult>();
		publisher.subscribe(sub);
		return sub.getSingleResult().getModifiedCount() > 0;
	}

	public Product findProductById(long id) {
		FindPublisher<Product> publisher = productColl.find(Filters.eq("_id", id));
		BikeSubscriber<Product> sub = new BikeSubscriber<>();
		publisher.subscribe(sub);
		return sub.getSingleResult();
	}

//	2. Tìm danh sách sản phẩm có giá cao nhất
// max = db.products.find().sort({price:-1}).limit()
// db.products.find({price:max})
	public List<Product> getProductMaxPrice() {
		Publisher<Product> first = productColl.find().sort(eq("price", -1)).limit(1).first();
		BikeSubscriber<Product> sub1 = new BikeSubscriber<Product>();
		first.subscribe(sub1);
		double max = sub1.getSingleResult().getPrice();
		FindPublisher<Product> find = productColl.find(eq("price", max));
		BikeSubscriber<Product> sub2 = new BikeSubscriber<Product>();
		find.subscribe(sub2);
		return sub2.getResults();
	}

//	3. Tìm danh sách sản phẩm chưa bán được lần nào
//	db.products.find({_id:{$nin:db.orders.distinct("order_details.product_id")}})
	public List<Product> getProudctNotOrder() {
		DistinctPublisher<Long> distinct = db.getCollection("orders").distinct("order_details.product_id", Long.class);
		BikeSubscriber<Long> sub1 = new BikeSubscriber<Long>();
		distinct.subscribe(sub1);
		List<Long> results = sub1.getResults();
		FindPublisher<Product> publisher = productColl.find(nin("_id", results));
		BikeSubscriber<Product> sub = new BikeSubscriber<Product>();
		publisher.subscribe(sub);
		return sub.getResults();
	}

//	8. Dùng text search để tìm kiếm sản phẩm theo tên sản phẩm
//	 db.products.createIndex({"product_name":"text"})
	public List<Product> findByTextIndex(String name) {
		Publisher<String> createIndex = docColl.createIndex(Indexes.text("product_name"));
		BikeSubscriber<String> sub1 = new BikeSubscriber<String>();
		createIndex.subscribe(sub1);
		FindPublisher<Product> find = productColl.find(text("\"" + name + "\""));
		BikeSubscriber<Product> sub = new BikeSubscriber<Product>();
		find.subscribe(sub);
		return sub.getResults();
	}

//	11.Cập nhật giá của sản phẩm khi biết mã sản phẩm
	public boolean updatePrice(long id, double newPrice) {
		Publisher<UpdateResult> publisher = productColl.updateOne(Filters.eq("_id", id),
				new Document("$set", new Document("price", newPrice)));
		BikeSubscriber<UpdateResult> sub = new BikeSubscriber<>();
		publisher.subscribe(sub);
		return sub.getSingleResult().getModifiedCount() > 0;
	}

//	Thêm sản phẩm không trùng
// db.products.createIndex({product_name:1},{unique:true})
	public boolean addNotDuplicateProdct(Product product) {
	        // Create a unique index on the product_name field
	        Publisher<String> createIndex = docColl.createIndex(new Document("product_name", 1), new IndexOptions().unique(true));
	        BikeSubscriber<String> sub1 = new BikeSubscriber<String>();
	        createIndex.subscribe(sub1);

	        // Insert the product into the collection
	        Publisher<InsertOneResult> insertOne = productColl.insertOne(product);
	        BikeSubscriber<InsertOneResult> sub2 = new BikeSubscriber<InsertOneResult>();
	        insertOne.subscribe(sub2);
	        Bson filter = Filters.eq("product_name", product.getName());
	        Publisher<Long> countDocuments = productColl.countDocuments(filter);
	        BikeSubscriber<Long> subCount =  new BikeSubscriber<>();
	        countDocuments.subscribe(subCount);
	        if (subCount.getSingleResult() > 0) 
	            return false;
	        
	        return sub2.getSingleResult().getInsertedId() != null;

	    
	}
}
