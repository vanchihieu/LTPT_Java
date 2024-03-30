package db;

import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;

public class Connection {
	private static Connection instance;
	private MongoClient mongoClient;
	
	private Connection() {
		mongoClient = MongoClients.create(); //Local host
	}
	
	public static Connection getInstance() {
		if(instance == null)
			instance = new Connection();
		return instance;
	}
	
	public MongoClient getMongoClient() {
		return mongoClient;
	}
}
