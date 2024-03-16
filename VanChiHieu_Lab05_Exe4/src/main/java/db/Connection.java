package db;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

public class Connection {
	private static Connection instance = null;
	private MongoClient client;

	public Connection() {
		client = MongoClients.create();
	}

	public static Connection getInstance() {
		if (instance == null)
			instance = new Connection();
		return instance;
	}

	public MongoClient getMongoClient() {
		return client;
	}
}
