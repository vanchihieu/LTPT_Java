package db;

import com.mongodb.ConnectionString;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class Connection {
	private static final String CONNECTION_STRING = "mongodb://localhost:27017";
	private static final String DATABASE_NAME = "sample_training";
	
	private MongoClient client;
	private MongoDatabase db;
	
	public Connection() {
		ConnectionString connectionString = new ConnectionString(CONNECTION_STRING);
		this.client = MongoClients.create(connectionString);
		this.db = this.client.getDatabase(DATABASE_NAME);
	}
	
	public MongoClient getClient() {
		return this.client;
	}
	
	public MongoDatabase getDatabase() {
		return this.db;
	}
	
	
}
