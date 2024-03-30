package dao;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.reactivestreams.Publisher;

import com.google.gson.Gson;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.InsertOneResult;
import com.mongodb.reactivestreams.client.FindPublisher;
import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoCollection;
import com.mongodb.reactivestreams.client.MongoDatabase;

import db.Connection;
import entity.Staff;

public class StaffDao {
	private MongoCollection<Document> docs;
	private final Gson GSON = new Gson();
	public StaffDao() {
		MongoClient client = Connection.getInstance().getClient();
		MongoDatabase db = client.getDatabase("BikeStores");
		docs = db.getCollection("staffs");
	}
	
	public Staff findById(long id) {
		Publisher<Document> doc = docs.find(Filters.eq("_id",id)).first();
		BikeSubscriber<Document> sub = new BikeSubscriber<>();
		doc.subscribe(sub);
		String json  = sub.getSingleResult().toJson();
		Long managerId = sub.getSingleResult().getLong("manager_id");
		Staff staff = GSON.fromJson(json,Staff.class);
		staff.setManager(new Staff(managerId));
		return staff;
	}
	
	public boolean insertStaff(Staff staff) {
		String json = GSON.toJson(staff);
		Document doc = Document.parse(json);
		System.out.println(doc);
		long id = doc.get("manager",Document.class).getInteger("_id");
		doc.remove("manager");
		doc.append("manager_id",id);
		Publisher<InsertOneResult> rs = docs.insertOne(doc);
		BikeSubscriber<InsertOneResult> sub = new BikeSubscriber<>();
		rs.subscribe(sub);
		return sub.getSingleResult().getInsertedId() != null;
	}
}
