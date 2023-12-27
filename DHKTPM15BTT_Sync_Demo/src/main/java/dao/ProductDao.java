package dao;

import com.mongodb.MongoClientException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.result.InsertManyResult;

import entity.Product;
import static com.mongodb.MongoClientSettings.getDefaultCodecRegistry;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

import java.util.List;

import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
public class ProductDao extends AbstractDao{
	private MongoCollection<Product> productCollection;
	
	public ProductDao(MongoClient client) {
		super(client);
		
		CodecProvider pojoCodecProvider = PojoCodecProvider.builder().automatic(true).build();
		CodecRegistry pojoCodecRegistry = fromRegistries(getDefaultCodecRegistry(), fromProviders(pojoCodecProvider));

		productCollection = db.getCollection("products", Product.class).withCodecRegistry(pojoCodecRegistry);

	}
	
	public int addProducts(List<Product> products) {
		try {
			InsertManyResult rs = productCollection.insertMany(products);
			
			return rs.getInsertedIds().size();
		} catch (MongoClientException e) {
			e.printStackTrace();
		}
		
		return 0;
	}
	
	
}
