package db;

import com.mongodb.ConnectionString;
import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;
import com.mongodb.reactivestreams.client.MongoDatabase;

public class Connection {
	private static final String CONNECTION_STRING = "mongodb://localhost:27017/";
	private static final String DB_NAME = "BikeStores"; // "ordersDB";
	
	private MongoClient client;
	private MongoDatabase db;
	
	private static Connection instance;
	
	public Connection() {
		ConnectionString connectionString = new ConnectionString(CONNECTION_STRING);
		this.client = MongoClients.create(connectionString);
		this.db = this.client.getDatabase(DB_NAME);
	}
	
	public static Connection getInstance() {
		if (instance == null) {
			instance = new Connection();
		}

		return instance;
	}
	
	public MongoClient getClient() {
		return this.client;
	}
	
	public MongoDatabase getDatabase() {
		return db;
	}
}
