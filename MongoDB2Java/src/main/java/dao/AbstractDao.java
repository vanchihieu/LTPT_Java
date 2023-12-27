package dao;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;

public abstract class AbstractDao {
	private MongoClient client;
	protected MongoDatabase db;

	public AbstractDao(MongoClient client) {
		this.client = client; // localhost:27017
		this.db = client.getDatabase("dbtest");
	}
	
	public MongoClient getClient() {
		return client;
	}
	
	public MongoDatabase getDatabase() {
		return db;
	}
	
}
