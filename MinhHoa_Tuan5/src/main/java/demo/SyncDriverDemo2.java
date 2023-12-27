package demo;

import org.bson.Document;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import entity.Lophoc;

public class SyncDriverDemo2 {
	public static void main(String[] args) {
		MongoClient client = MongoClients.create();
		MongoDatabase db = client.getDatabase("dbtest");
		MongoCollection<Document> coll = db.getCollection("lopHoc");
		
//		db.lopHoc.find({maLop:'DHKTPM17B'})
		Document fillter = new Document("maLop", "DHKTPM17B");
		
		Document doc = coll.find(fillter).first();
		System.out.println(doc);
		
		Lophoc lh = new Lophoc(doc.getString("maLop"), doc.getString("tenLop"), doc.getInteger("khoaHoc"), doc.getString("maNganh"));
		System.out.println(lh);
	}
}
