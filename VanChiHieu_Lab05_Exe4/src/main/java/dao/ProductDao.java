package dao;

import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.Document;
import org.bson.codecs.configuration.CodecProvider;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.InsertManyResult;
import com.mongodb.client.result.InsertOneResult;
import com.mongodb.client.result.UpdateResult;

import db.Connection;
import entities.Product;

import static com.mongodb.MongoClientSettings.getDefaultCodecRegistry;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

import java.util.ArrayList;
import java.util.List;

public class ProductDao {
	private MongoCollection<Product> productColl;
	private MongoCollection<Document> docColl;

	MongoDatabase db;

	public ProductDao() {
		MongoClient client = Connection.getInstance().getMongoClient();
		db = client.getDatabase("BikeStores");

		CodecProvider pojoCodecProvider = PojoCodecProvider.builder().automatic(true).build();
		CodecRegistry pojoCodecRegistry = fromRegistries(getDefaultCodecRegistry(), fromProviders(pojoCodecProvider));

		productColl = db.getCollection("products", Product.class).withCodecRegistry(pojoCodecRegistry);
		docColl = db.getCollection("products");
	}

//	1.CRUD với các phương thức
//	a.1.Thêm một document
	public boolean insertProduct(Product product) {
		InsertOneResult result = productColl.insertOne(product);
		if (result.getInsertedId() != null) {
			return true;
		}
		return false;
	}

	// a.2.Thêm nhiều document
	public boolean insertManyProducts(List<Product> products) {
		InsertManyResult result = productColl.insertMany(products);
		if (result.getInsertedIds() != null)
			return true;
		return false;
	}

//	b. Cập nhật giá trị thuộc tính của một hoặc nhiều document: updateOne, updateMany
//	b.1 Cập nhật giá trị của một document
	public boolean updateProduct(Product product) {
		UpdateResult result = productColl.updateOne(Filters.eq("_id", product.getProductId()),
				new Document("$set", product));

		return result.getModifiedCount() > 0 ? true : false;
	}

//	b.2 Cập nhật gái trị của nhiều document
	public boolean updateManyProducts(String category, double price) {
		UpdateResult result = productColl.updateMany(Filters.eq("category_name", category),
				new Document("$set", new Document("price", price)));

		return result.getModifiedCount() > 0 ? true : false;
	}

//	c.Tìm kiếm và thay thế: findOneAndUpdate.
	public boolean findOneAndUpdateProduct(String category, double price) {
		Product product = productColl.findOneAndUpdate(Filters.eq("category_name", category),
				new Document("$set", new Document("price", price)));

		return product != null ? true : false;
	}

//	d. Xóa một hoặc nhiều document: deleteOne, deleteMany, findOneAndDelete
//	Delete One
	public boolean deleteProduct(long productId) {
		DeleteResult product = productColl.deleteOne(Filters.eq("_id", productId));
		return product.getDeletedCount() > 0 ? true : false;
	}
	
	//Delete Many
	public boolean deleteProductsByCategory(String category) {
		DeleteResult products = productColl.deleteMany(Filters.eq("category_name", category));
		return products.getDeletedCount() > 0 ? true : false;
	}
	
	//Find One And Delete
	public Product findOneAndDeleteProduct(String category) {
		Product product = productColl.findOneAndDelete(Filters.eq("category_name", category));
		return product;
	}
	
//	e. Thêm/xóa thuộc tính của document
//	e.1 Thêm một thuộc tính của document
	public boolean addColorToProduct(long productId, String color) {
		UpdateResult result = productColl.updateOne(Filters.eq("_id", productId),
				new Document("$push", new Document("colors", color)));
		return result.getModifiedCount() > 0 ? true : false;
	}
	
	// e.2 Xóa một thuộc tính của document
	public boolean removeColorFromProduct(long productId, String color) {
		UpdateResult result = productColl.updateOne(Filters.eq("_id", productId),
				new Document("$pull", new Document("colors", color)));
		return result.getModifiedCount() > 0 ? true : false;
	}

//	f. Tìm kiếm theo mã: Tìm khách hàng, nhân viên, sản phẩm, hóa đơn khi biết mã số
	public Product findProductById(long productId) {
		Product product = productColl.find(Filters.eq("_id", productId)).first();
		return product;
	}
	
//	2. Tìm danh sách sản phẩm có giá cao nhất.
	// db.products.find().sort({price: -1}).limit(5)
	public List<Product> findProductsWithMaxPrice() {
		List<Product> products = productColl.find().sort(new Document("price", -1)).limit(5)
				.into(new ArrayList<Product>());
		return products;
	}
	
//	3. Tìm danh sách sản phẩm chưa bán được lần nào.
//	db.products.find({_id:{$nin:db.orders.distinct("order_details.product_id")}})
	
	public List<Product> findProductsNeverSold() {
		List<Long> productIds = db.getCollection("orders").distinct("order_details.product_id", Long.class)
				.into(new ArrayList<Long>());
		List<Product> products = productColl.find(Filters.nin("_id", productIds)).into(new ArrayList<Product>());
		return products;
	}
	
//	8.Dùng text search để tìm kiếm sản phẩm theo tên sản phẩm.
	//	db.products.createIndex({product_name: "text"})
	//	db.products.find({$text: {$search: "name"}})
	public List<Product> searchProductByName(String name) {
		docColl.createIndex(new Document("product_name", "text"));
		List<Product> products = productColl.find(Filters.text(name)).into(new ArrayList<Product>());
		return products;
	}
	
//	11.Cập nhật giá của sản phẩm khi biết mã sản phẩm.
	//	db.products.updateOne({_id: 1}, {$set: {price: 100}})
	public boolean updateProductPrice(long productId, double price) {
		UpdateResult result = productColl.updateOne(Filters.eq("_id", productId),
				new Document("$set", new Document("price", price)));
		return result.getModifiedCount() > 0 ? true : false;
	}
}
