package demo;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class SyncDriverDemo {
	public static void main(String[] args) {
		MongoClient client = MongoClients.create();
		
		client.listDatabaseNames()
		.forEach(dbName -> {
			System.out.println(dbName);
			MongoDatabase db = client.getDatabase(dbName);
			db.listCollectionNames()
			.forEach(colName -> {
				System.out.println("\t\t" + colName);
			});
		});
//		MongoDatabase db = client.getDatabase()
	}
}
