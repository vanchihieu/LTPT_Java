package dao;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.bson.Document;

import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Accumulators;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Sorts;

public class  DaoUtil extends AbstractDao{
	private MongoCollection<Document> mongoCollection;
	
	public DaoUtil(MongoClient client) {
		super(client);
		mongoCollection = db.getCollection("products");
	}
	
//	db.products.aggregate([{$group: {_id: '$model_year', num:{$sum: 1}}, {$sort: {num: -1}}])
	public Map<Integer, Integer> getTotalByYear() {
		
		Document filter = new Document("$group", new Document("_id", "$model_year").append("num", new Document("$sum", 1)));
		Document sort = new Document("$sort", new Document("num", -1));
		
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		
		AggregateIterable<Document> docs = mongoCollection.aggregate(Arrays.asList(
					filter,
					sort
				));
		
		MongoCursor<Document> it = docs.iterator();
		while(it.hasNext()) {
			Document doc = it.next();
			map.put(doc.getInteger("_id"), doc.getInteger("num"));
		}
		
		return map;
	}
	
//	db.products.aggregate([{$group: {_id: '$model_year', num:{$sum: 1}}, {$sort: {num: -1}}])
	public Map<Integer, Integer> getTotalByYear2() {
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();

		AggregateIterable<Document> docs = mongoCollection.aggregate(Arrays.asList(
					Aggregates.group("$model_year", Accumulators.sum("num", 1)),
					Aggregates.sort(Sorts.descending("num"))
				));
		
		MongoCursor<Document> it = docs.iterator();
		while(it.hasNext()) {
			Document doc = it.next();
			map.put(doc.getInteger("_id"), doc.getInteger("num"));
		}
		
		return map;
	}
}
