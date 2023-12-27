package dao;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;

public class AbstractDao {
	private MongoClient client;
	protected MongoDatabase db;

	public AbstractDao(MongoClient client) {
		this.client = client;
		this.db = client.getDatabase("dbtest");
	}

	public MongoClient getClient() {
		return client;
	}

	public void setClient(MongoClient client) {
		this.client = client;
	}

	public MongoDatabase getDatabase() {
		return db;
	}

	public void setDb(MongoDatabase db) {
		this.db = db;
	}
	
	
}
