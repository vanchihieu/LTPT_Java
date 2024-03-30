package dao;

import com.mongodb.client.model.Accumulators;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.InsertOneResult;
import com.mongodb.client.result.UpdateResult;
import com.mongodb.reactivestreams.client.AggregatePublisher;
import com.mongodb.reactivestreams.client.FindPublisher;
import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoCollection;
import com.mongodb.reactivestreams.client.MongoDatabase;

import db.Connection;
import entities.Zip;

import static com.mongodb.MongoClientSettings.getDefaultCodecRegistry;
import static com.mongodb.client.model.Filters.eq;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bson.Document;
import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.reactivestreams.Publisher;

public class ZipDao {
	private MongoCollection<Zip> zipCollection;
	private MongoCollection<Document> docColl;

	private MongoDatabase db;

	public ZipDao() {
		MongoClient client = Connection.getInstance().getClient();
		db = client.getDatabase("sample_training");

		CodecProvider pojoCodecProvider = PojoCodecProvider.builder().automatic(true).build();

		CodecRegistry pojoCodecRegistry = fromRegistries(getDefaultCodecRegistry(), fromProviders(pojoCodecProvider));

		zipCollection = db.getCollection("zips", Zip.class).withCodecRegistry(pojoCodecRegistry);
		docColl = db.getCollection("zips");
	}

	// get all documents
	public List<Zip> displayDocuments(int k, int n) {
		FindPublisher<Zip> find = zipCollection.find().skip(k).limit(n);
		ZipSubscriber<Zip> sub = new ZipSubscriber<Zip>();
		find.subscribe(sub);
		return sub.getResults();
	}

	// 2. Chèn thêm 1 document mới
	public boolean insertDocument(Zip zip) {
		Publisher<InsertOneResult> insertOne = zipCollection.insertOne(zip);
		ZipSubscriber<InsertOneResult> sub = new ZipSubscriber<InsertOneResult>();
		insertOne.subscribe(sub);
		return sub.getSingleResult().wasAcknowledged();
	}
	
	// xóa document
	public  boolean deleteDocument(Object id) {
		Publisher<DeleteResult> delete = zipCollection.deleteOne(eq("_id", id));
		ZipSubscriber<DeleteResult> sub = new ZipSubscriber<DeleteResult>();
		delete.subscribe(sub);
		return sub.getSingleResult().wasAcknowledged();
	}
	// 3. Cập nhật thông tin của một document khi biết id
	// db.zips.updateOne({_id:null},{$set:{state:'Hieu'}})
	public boolean updateDocument(Object id, String state) {
		Publisher<UpdateResult> update = zipCollection.updateOne(eq("_id", id),
				new Document("$set", new Document("state", state)));
		ZipSubscriber<UpdateResult> sub = new ZipSubscriber<UpdateResult>();
		update.subscribe(sub);
		return sub.getSingleResult().wasAcknowledged();
	}

	// 4. Tìm các document có city là PALMER
	// db.zips.find({city:'PALMER'})
	public List<Zip> findDocumentByCity(String city) {
		FindPublisher<Zip> find = zipCollection.find(eq("city", city));
		ZipSubscriber<Zip> sub = new ZipSubscriber<Zip>();
		find.subscribe(sub);
		return sub.getResults();
	}

	// 5. Tìm các document có dân số >100000
	// db.zips.find({pop:{$gt:100000}})
	public List<Zip> findDocumentByPop(int pop) {
		FindPublisher<Zip> find = zipCollection.find(eq("pop", pop));
		ZipSubscriber<Zip> sub = new ZipSubscriber<Zip>();
		find.subscribe(sub);
		return sub.getResults();
	}

	// 6. Tìm dân số của thành phố FISHERS ISLAND
	// db.zips.find({city:'FISHERS ISLAND'},{pop:1})
	public List<Zip> findPopByCity(String city) {
		FindPublisher<Zip> find = zipCollection.find(eq("city", city)).projection(new Document("pop", 1));
		ZipSubscriber<Zip> sub = new ZipSubscriber<Zip>();
		find.subscribe(sub);
		return sub.getResults();
	}

	// 7. Tìm các thành phố có dân số từ 10 – 50
	// db.zips.find({pop:{$gt:10, $lt:50}})
	public List<Zip> findCityByPop(int min, int max) {
		FindPublisher<Zip> find = zipCollection.find(new Document("pop", new Document("$gt", min).append("$lt", max)));
		ZipSubscriber<Zip> sub = new ZipSubscriber<Zip>();
		find.subscribe(sub);
		return sub.getResults();
	}

	// 8. Tìm tất cả các thành phố của bang MA có dân số trên 500
	// db.zips.find({state:'MA', pop:{$gt:500}})
	public List<Zip> findCityByStateAndPop(String state, int pop) {
		FindPublisher<Zip> find = zipCollection
				.find(new Document("state", state).append("pop", new Document("$gt", pop)));
		ZipSubscriber<Zip> sub = new ZipSubscriber<Zip>();
		find.subscribe(sub);
		return sub.getResults();
	}

	// 9. Tìm tất cả các bang (không trùng)
	// db.zips.distinct('state')
	public List<String> findDistinctState() {
		FindPublisher<String> find = (FindPublisher<String>) zipCollection.distinct("state", String.class);
		ZipSubscriber<String> sub = new ZipSubscriber<String>();
		find.subscribe(sub);
		return sub.getResults();
	}

	// 10.Tìm tất cả các bang mà có chứa ít nhất 1 thành phố có dân số trên 100000
	// db.zips.distinct("state", { "pop": { $gt: 100000 } })
	public List<String> findStateByPop(int pop) {
		FindPublisher<String> find = (FindPublisher<String>) zipCollection.distinct("state",
				new Document("pop", new Document("$gt", pop)), String.class);
		ZipSubscriber<String> sub = new ZipSubscriber<String>();
		find.subscribe(sub);
		return sub.getResults();
	}

	// 11.Tính dân số trung bình của mỗi bang
	// db.zips.aggregate([{$group:{_id:'$state', avgPop:{$avg:'$pop'}}}])
	public Map<String, Double> averagePopByState() {
		Map<String, Double> map = new HashMap<>();

		AggregatePublisher<Document> aggregate = docColl
				.aggregate(Arrays.asList(Aggregates.group("$state", Accumulators.avg("avgPop", "$pop"))));

		ZipSubscriber<Document> sub = new ZipSubscriber<Document>();

		aggregate.subscribe(sub);
		List<Document> results = sub.getResults();
		for (Document doc : results) {
			map.put(doc.getString("_id"), doc.getDouble("avgPop"));
		}
		return map;
	}

	// 12.Tìm những document của bang 'CT' và thành phố 'WATERBURY'
	// db.zips.find({state:'CT', city:'WATERBURY'})
	public List<Zip> findDocumentByStateAndCity(String state, String city) {
		FindPublisher<Zip> find = zipCollection.find(new Document("state", state).append("city", city));
		ZipSubscriber<Zip> sub = new ZipSubscriber<Zip>();
		find.subscribe(sub);
		return sub.getResults();
	}

//	15.Tìm tất cả các bang có tổng dân số trên 10000000
	// db.zips.aggregate([{$group:{_id:'$state',total:{$sum:'$pop'}}},{$match:{total:{$gt:10000000}}}])
	public Map<String, Integer> findStateByTotalPop(int pop) {
		Map<String, Integer> map = new HashMap<>();
		AggregatePublisher<Document> aggregate = docColl
				.aggregate(Arrays.asList(Aggregates.group("$state", Accumulators.sum("total", "$pop")),
						Aggregates.match(new Document("total", new Document("$gt", pop)))));
		ZipSubscriber<Document> sub = new ZipSubscriber<Document>();
		aggregate.subscribe(sub);
		List<Document> results = sub.getResults();
		for (Document doc : results) {
			map.put(doc.getString("_id"), doc.getInteger("total"));
		}
		return map;
	}

	// 16.Tìm các document có dân số lớn nhất
	// db.zips.aggregate([{$group:{_id:null, ps:{$addToSet:'$$ROOT'},maxPop:{$max:'$pop'}}},{$unwind:'$ps'},{$match:{$expr:{$eq:['$ps.pop','$maxPop']}}},{$replaceWith:'$ps'}])

	public Map<String, Integer> findStateByMaxPop() {
		Map<String, Integer> map = new HashMap<>();
		AggregatePublisher<Document> aggregate = docColl.aggregate(Arrays.asList(
				Aggregates.group(null, Accumulators.max("maxPop", "$pop"), Accumulators.addToSet("ps", "$$ROOT")),
				Aggregates.unwind("$ps"),
				Aggregates.match(new Document("$expr", new Document("$eq", Arrays.asList("$ps.pop", "$maxPop")))),
				Aggregates.replaceWith("$ps")));
		ZipSubscriber<Document> sub = new ZipSubscriber<Document>();
		aggregate.subscribe(sub);
		List<Document> results = sub.getResults();
		for (Document doc : results) {
			map.put(doc.getString("state"), doc.getInteger("pop"));
		}
		return map;
	}
	
    public List<Zip> findDocumentByPopMax() {
        AggregatePublisher<Zip> aggregate = zipCollection.aggregate(Arrays.asList(
                Aggregates.group(null, Accumulators.max("maxPop", "$pop"),
                        Accumulators.addToSet("ps", "$$ROOT")),
                Aggregates.unwind("$ps"),
                Aggregates.match(new Document("$expr", new Document("$eq", Arrays.asList("$ps.pop", "$maxPop")))),
                Aggregates.replaceWith("$ps")
        ));
        ZipSubscriber<Zip> sub = new ZipSubscriber<Zip>();
        aggregate.subscribe(sub);
        return sub.getResults();
    }

	// 17.Tìm bang có tổng dân số lớn nhất
	// db.zips.aggregate([{$group:{_id:'$state',
	// totalPop:{$sum:'$pop'}}},{$sort:{totalPop:-1}},{$limit:1}])
	public Map<String, Integer> findStateByTotalPopMax() {
		Map<String, Integer> map = new HashMap<>();
		AggregatePublisher<Document> aggregate = docColl
				.aggregate(Arrays.asList(Aggregates.group("$state", Accumulators.sum("totalPop", "$pop")),
						Aggregates.sort(new Document("totalPop", -1)), Aggregates.limit(1)));
		ZipSubscriber<Document> sub = new ZipSubscriber<Document>();
		aggregate.subscribe(sub);
		List<Document> results = sub.getResults();
		for (Document doc : results) {
			map.put(doc.getString("_id"), doc.getInteger("totalPop"));
		}
		return map;
	}

}
