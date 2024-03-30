package dao;

import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistry;
import static org.bson.codecs.configuration.CodecRegistries.*;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.conversions.Bson;
import org.reactivestreams.Publisher;

import com.mongodb.MongoClientSettings;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.UpdateResult;
import com.mongodb.reactivestreams.client.ClientSession;
import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoCollection;
import com.mongodb.reactivestreams.client.MongoDatabase;

import db.Connection;
import entity.Product;

public class ProductDao {
	private MongoCollection<Product> productColl;
	private MongoCollection<Document> docColl;
	
	public ProductDao() {
		MongoClient client = Connection.getInstance().getClient();
		MongoDatabase db = client.getDatabase("BikeStores");
		CodecRegistry pojoCodecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),
                fromProviders(PojoCodecProvider.builder().automatic(true).build()));
		productColl = db.getCollection("products",Product.class).withCodecRegistry(pojoCodecRegistry);
		docColl = db.getCollection("products");
	}
	
	public boolean updateQuantity(ClientSession clientSession,long id,int quaity) {
		Bson inc = Updates.inc("quantity",quaity);
		Bson filter = Filters.eq("_id", id);
		Publisher<UpdateResult> updateOne = productColl.updateOne(filter, inc);
		BikeSubscriber<UpdateResult> sub = new BikeSubscriber<>();
		updateOne.subscribe(sub);
		UpdateResult singleResult = sub.getSingleResult();
		if(singleResult.getMatchedCount() == 0 || singleResult.getModifiedCount() == 0)
			throw new RuntimeException("update fail");
		return singleResult.getModifiedCount() > 0;
	}
}
