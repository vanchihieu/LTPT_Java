package dao;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import com.mongodb.MongoClientSettings;
import com.mongodb.client.model.Accumulators;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.InsertOneResult;
import com.mongodb.client.result.UpdateResult;
import com.mongodb.reactivestreams.client.AggregatePublisher;
import com.mongodb.reactivestreams.client.FindPublisher;
import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoCollection;
import com.mongodb.reactivestreams.client.MongoDatabase;

import db.Connection;
import entity.Zip;

public class ZipDao {
//	Thao tác trực tiếp qua collection không thông qua cái mappeer
//	BsonDocument
//	Document
	private MongoCollection<Zip> zipColl;
	private MongoCollection<Document> docColl;
	
	public ZipDao() {
		MongoClient mongoClient = Connection.getInstance().getMongoClient();
		MongoDatabase db = mongoClient.getDatabase("sample_training");
		
		CodecRegistry codecRegistry  = CodecRegistries
				.fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),
							CodecRegistries.fromProviders(PojoCodecProvider.builder().automatic(true).build())
						);
		
		zipColl = db.getCollection("zips", Zip.class)
				.withCodecRegistry(codecRegistry  );
		
		docColl = db.getCollection("zips");
	}
	
//	db.zips.find({_id: ObjectId("5c8eccc1caa187d17ca6ed19")}
	public Zip findById(ObjectId id) {
		Publisher<Zip> publisher = zipColl.find(Filters.eq("_id", id)).first();
	
		ZipSubscriber<Zip> subscriber = new ZipSubscriber<Zip>();
		
		publisher.subscribe(subscriber);
		
		return subscriber.getOneReceived();
	}
	
//	db.zips.aggregate([{ $group: { _id: '$state', total: { $sum: '$pop' } } }])
	public Map<String, Integer> getSumOfPopByState() {
		
		Map<String, Integer> map = new HashMap<String, Integer>();
//		Publisher : tạo ra data
		AggregatePublisher<Document> publisher = docColl.aggregate(List.of(
					Aggregates.group("$state", Accumulators.sum("total", "$pop"))
				));
//		Subscription: Tiêu thụ (Xử lý) phần data tạo ra
		
//		Subscribe: đang ký để xử lý dữ liệu
		ZipSubscriber<Document> subscriber = new ZipSubscriber<Document>();
		
		publisher.subscribe(subscriber);
		
		List<Document> list = subscriber.getReceived();
		for(Document doc : list) {
			map.put(doc.getString("_id"), doc.getInteger("total"));
		}
		
		return map;
	}
	
	
	
//	Chèn thêm 1 document mới
	public boolean addZip(Zip zip) throws InterruptedException {
		AtomicBoolean rs = new AtomicBoolean();
		CountDownLatch latch = new CountDownLatch(1);
		Publisher<InsertOneResult> pub = zipColl.insertOne(zip);
		Subscriber<InsertOneResult> sub = new Subscriber<InsertOneResult>() {	
			@Override
			public void onSubscribe(Subscription s) {
				s.request(1);
			}

			@Override
			public void onNext(InsertOneResult t) {
				if(t.getInsertedId()!=null) {
					rs.set(true);
				}
			}

			@Override
			public void onError(Throwable t) {
				t.printStackTrace();
			}

			@Override
			public void onComplete() {
				latch.countDown();
			}
		};
		
		pub.subscribe(sub);
		latch.await();
		return rs.get();
	}
	
//	 Cập nhật thông tin của một document khi biết id
	public boolean updateZipById(Zip zip) throws InterruptedException {
		AtomicBoolean rs = new AtomicBoolean();
		CountDownLatch latch = new CountDownLatch(1);
		Publisher<UpdateResult> pub = zipColl.replaceOne(Filters.eq("_id",zip.getId()), zip);
		Subscriber<UpdateResult> sub = new Subscriber<UpdateResult>() {

			@Override
			public void onSubscribe(Subscription s) {
				s.request(1);
				
			}

			@Override
			public void onNext(UpdateResult t) {
				if(t.getUpsertedId() != null) {
					rs.set(true);
				}
			}
			@Override
			public void onError(Throwable t) {
				t.printStackTrace();
			}
			@Override
			public void onComplete() {
				latch.countDown();
			}
		};
		pub.subscribe(sub);
		latch.await();
		return rs.get();
	}

//	Delete khi biet Id
	public boolean deleteZipById(ObjectId id) throws InterruptedException {
		AtomicBoolean rs = new AtomicBoolean();
		CountDownLatch latch = new CountDownLatch(1);
		Publisher<DeleteResult> pub = zipColl.deleteOne(Filters.eq("_id",id));
		Subscriber<DeleteResult> sub = new Subscriber<DeleteResult>() {
			@Override
			public void onSubscribe(Subscription s) {
				s.request(1);
			}

			@Override
			public void onNext(DeleteResult t) {
				if(t.getDeletedCount() > 0) {
					rs.set(true);
				}
			}

			@Override
			public void onError(Throwable t) {
				t.printStackTrace();
			}

			@Override
			public void onComplete() {
				latch.countDown();
			}
		};
		pub.subscribe(sub);
		latch.await();
		return rs.get();
	}
	
//	Tìm các document có city là PALMER
	public List<Zip> findCity(String city) throws InterruptedException{
		FindPublisher<Zip> pub = zipColl.find(Filters.eq("city",city));
		ZipSubscriber<Zip> sub = new ZipSubscriber<Zip>();
		pub.subscribe(sub);
		List<Zip> zipList = sub.getReceived();
		return zipList;
	}
	
//	Tìm các document có dân số >100000
	public List<Zip> findByPop(int pop) throws InterruptedException{
		FindPublisher<Zip> pub = zipColl.find(Filters.gt("pop",pop));
		ZipSubscriber<Zip> sub = new ZipSubscriber<Zip>();
		pub.subscribe(sub);
		List<Zip> zipList = sub.getReceived();
		return zipList;
	}
	
//	Tìm các thành phố có dân số từ 10 – 50
	public Map<String,Integer> getCityByPop(){
		Map<String, Integer> map = new HashMap<String, Integer>();
		AggregatePublisher<Document> publisher = docColl.aggregate(List.of(
					Document.parse("{$match:{city:'FISHERS ISLAND'}}"),
					Document.parse("{$project:{'pop':1,'_id':0}}")
				));
		ZipSubscriber<Document> subscriber = new ZipSubscriber<Document>();
		
		publisher.subscribe(subscriber);
		
		List<Document> list = subscriber.getReceived();
		for(Document doc : list) {
			map.put(doc.getString("pop"), doc.getInteger("pop"));
		}
		
		return map;
	}
	
	

}




