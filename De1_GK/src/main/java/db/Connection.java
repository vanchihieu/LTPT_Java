package db;

import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;

public class Connection {
	private static Connection instance = null;
	private MongoClient client;
	private Connection() {
		client = MongoClients.create();
	}
	public static Connection getInstance() {
		if(instance == null)
			instance = new Connection();
		return instance;
	}
	public MongoClient getClient() {
		return client;
	}
}
