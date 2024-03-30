package dao;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.types.ObjectId;
import org.reactivestreams.Publisher;

import com.mongodb.MongoClientSettings;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.reactivestreams.client.ClientSession;
import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoCollection;
import com.mongodb.reactivestreams.client.MongoDatabase;

import db.Connection;
import entity.Order;

public class OrderDao {
	private MongoCollection<Order> orderColl;
	private MongoCollection<Document> docColl;
	
	public OrderDao() {
		MongoClient client = Connection.getInstance().getClient();
		MongoDatabase db = client.getDatabase("BikeStores");
		CodecRegistry pojoCodecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),
                fromProviders(PojoCodecProvider.builder().automatic(true).build()));
		orderColl = db.getCollection("orders",Order.class).withCodecRegistry(pojoCodecRegistry);
		docColl = db.getCollection("orders");
	}
	
	public boolean deleteOrder(ClientSession clientSession,ObjectId orderId,long productId) {
		Publisher<DeleteResult> deleteOne = orderColl.deleteOne(new Document("_id", orderId).append("product_id",productId));
		BikeSubscriber<DeleteResult> sub = new BikeSubscriber<>();
		deleteOne.subscribe(sub);
		long deletedCount = sub.getSingleResult().getDeletedCount();
		if(deletedCount == 0)
			throw new RuntimeException("delete fail");
		return deletedCount > 0 ;
	}
}
