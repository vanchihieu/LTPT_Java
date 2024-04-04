package dao;

import org.bson.Document;
import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.conversions.Bson;
import org.reactivestreams.Publisher;

import com.mongodb.MongoClientSettings;
import com.mongodb.client.model.Accumulators;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Indexes;
import com.mongodb.client.model.Sorts;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import com.mongodb.reactivestreams.client.AggregatePublisher;
import com.mongodb.reactivestreams.client.FindPublisher;
import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoCollection;
import com.mongodb.reactivestreams.client.MongoDatabase;

import db.Connection;
import entities.Restaurant;

import static com.mongodb.MongoClientSettings.getDefaultCodecRegistry;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

import java.util.Arrays;
import java.util.List;

public class RestaurantDao {
	private MongoCollection<Restaurant> restaurantColl;

	private MongoCollection<Document> docColl;

	private MongoDatabase db;

	public RestaurantDao() {
		MongoClient client = Connection.getInstance().getClient();
		db = client.getDatabase("dbtest");

		CodecProvider pojoCodecProvider = PojoCodecProvider.builder().automatic(true).build();

		CodecRegistry pojoCodecRegistry = fromRegistries(getDefaultCodecRegistry(), fromProviders(pojoCodecProvider));
		restaurantColl = db.getCollection("restaurants", Restaurant.class).withCodecRegistry(pojoCodecRegistry);
		docColl = db.getCollection("restaurants");
	}

	// get all restaurants
	public List<Restaurant> getRestaurants() {
		FindPublisher<Restaurant> find = restaurantColl.find();
		RestaurantSubcriber<Restaurant> sub = new RestaurantSubcriber<>();
		find.subscribe(sub);
		return sub.getResults();
	}

	// dùng text search để tìm kiếm các nhà hầng dựa theo borough và cuisine
	// db.restaurants.createIndex({borough:"text", cuisine:"text"})
	// db.restaurants.find({$text:{$search:"brooklyn pizza"}})
	public List<Restaurant> getRestaurants(String borough, String cuisine) {
		Bson keys = Indexes.compoundIndex(Indexes.text("borough"), Indexes.text("cuisine"));
		Publisher<String> index = restaurantColl.createIndex(keys);
		RestaurantSubcriber<String> subIndex = new RestaurantSubcriber<>();
		index.subscribe(subIndex);

		FindPublisher<Restaurant> list = restaurantColl.find(Filters.text(borough + " " + cuisine));
		RestaurantSubcriber<Restaurant> sub = new RestaurantSubcriber<>();
		list.subscribe(sub);
		return sub.getResults();
	}

	public List<Restaurant> searchRestaurant(String text) {
		Bson keys = Indexes.compoundIndex(Indexes.text("borough"), Indexes.text("cuisine"));
		Publisher<String> index = restaurantColl.createIndex(keys);
		RestaurantSubcriber<String> subIndex = new RestaurantSubcriber<>();
		index.subscribe(subIndex);
		
		FindPublisher<Restaurant> list = restaurantColl.find(Filters.text(text));
		RestaurantSubcriber<Restaurant> sub = new RestaurantSubcriber<>();
		list.subscribe(sub);
		return sub.getResults();
	}

//	/**
//	 *  db.products.aggregate([
//	 *  	{ $group: { _id: null, ps: { $addToSet: '$$ROOT' }, maxPrice: { $max: '$price' } } }, 
//	 *  	{ $unwind: '$ps' }, 
//	 *  	{ $match: { $expr: { $eq: ['$ps.price', '$maxPrice'] } } }, 
//	 *  	{$replaceWith: '$ps'}
//	 *  ] )
//	 *  
//	 */

	// tính điểm trung bình (score) của từng nhà hàng, sắp xếp giảm dần theo điểm
	// trung bình. Lấy 5 nhà hàng có điểm trung bình cao nhất
	// db.restaurants.aggregate([{$unwind:'$grades'}
	// ,{$group:{_id:"$_id",avgScore:{$avg:'$grades.score'}}}
	// ,{$sort:{avgScore:-1}}
	// ,{$limit:5}])

	public List<Document> getTop5Restaurant() {
		AggregatePublisher<Document> aggregate = docColl.aggregate(Arrays.asList(Aggregates.unwind("$grades"),
				Aggregates.group("$_id", Accumulators.avg("avgScore", "$grades.score")),
				Aggregates.sort(Sorts.descending("avgScore")), Aggregates.limit(5)));

		RestaurantSubcriber<Document> sub = new RestaurantSubcriber<>();
		aggregate.subscribe(sub);
		return sub.getResults();
	}

	public List<Restaurant> getTop5Restaurant2() {
		AggregatePublisher<Restaurant> aggregate = restaurantColl.aggregate(Arrays.asList(Aggregates.unwind("$grades"),
				Aggregates.group("$_id", Accumulators.avg("avgScore", "$grades.score")),
				Aggregates.sort(Sorts.descending("avgScore")), Aggregates.limit(5)));

		RestaurantSubcriber<Restaurant> sub = new RestaurantSubcriber<>();
		aggregate.subscribe(sub);
		return sub.getResults();
	}

	// cập nhật thông tin building của Addresss khi biết restaurantId và name của
	// restaurant
	// db.restaurants.updateOne({restaurant_id:"30075445", name: "Riviera
	// Caterer"},{$set:{"address.building":"999"}})
	public boolean updateAddress(String restaurantId, String name, String building) {
		Publisher<UpdateResult> update = restaurantColl.updateOne(

				Filters.and(Filters.eq("restaurant_id", restaurantId), Filters.eq("name", name)),
				Updates.set("address.building", building));
//				new Document("$set", new Document("address.building", building)));
		RestaurantSubcriber<UpdateResult> sub = new RestaurantSubcriber<>();
		update.subscribe(sub);
		return sub.getSingleResult().wasAcknowledged();
	}

	// lấy danh sách borough có nhà hàng đạt điểm (score) >= 90 và grade = 'A'. Kết
	// quả sắp xếp theo tên nhà hàng
	// db.restaurants.aggregate([{$unwind:'$grades'},{$match:{'grades.score':{$gte:90},'grades.grade':'A'}},{$group:{_id:'$borough'}},{$sort:{_id:1}}])

	public List<Document> getRestaurantByScoreAndGrade() {
		AggregatePublisher<Document> aggregate = docColl.aggregate(Arrays.asList(Aggregates.unwind("$grades"),
				Aggregates.match(Filters.and(Filters.gte("grades.score", 9), Filters.eq("grades.grade", "A"))),
				Aggregates.group("$borough"), Aggregates.sort(Sorts.ascending("_id"))));
		RestaurantSubcriber<Document> sub = new RestaurantSubcriber<>();
		aggregate.subscribe(sub);
		return sub.getResults();
	}

//	db.restaurants.aggregate([
//	                          { $group: { _id: null, ps: { $addToSet: '$$ROOT' }}}, 
//	                           { $unwind: '$ps' },
//	                            {$unwind: '$ps.grades'},
//	                            {$match: { 'ps.grades.grade': 'A', 'ps.grades.score': { $gte: 9 } } },
//	                            { $replaceWith: '$ps' },
//	                            { $sort: { name: 1 } }
//	                        ])
	public List<Restaurant> getRestaurantByScoreAndGrade2() {
		AggregatePublisher<Restaurant> aggregate = restaurantColl
				.aggregate(Arrays.asList(Aggregates.group(null, Accumulators.addToSet("ps", "$$ROOT")),
						Aggregates.unwind("$ps"), Aggregates.unwind("$ps.grades"),
						Aggregates.match(
								Filters.and(Filters.eq("ps.grades.grade", "A"), Filters.gte("ps.grades.score", 9))),
						Aggregates.replaceWith("$ps"), Aggregates.sort(Sorts.ascending("name"))));
		RestaurantSubcriber<Restaurant> sub = new RestaurantSubcriber<>();
		aggregate.subscribe(sub);
		return sub.getResults();
	}
	
	// xoá nhà hàng theo restaurant_id
	// db.restaurants.deleteOne({restaurant_id:"30075445"})
	public boolean deleteRestaurant(String restaurantId) {
		Publisher<DeleteResult> delete = restaurantColl.deleteOne(Filters.eq("restaurant_id", restaurantId));
		RestaurantSubcriber<DeleteResult> sub = new RestaurantSubcriber<DeleteResult>();
		delete.subscribe(sub);
		return sub.getSingleResult().wasAcknowledged();
	}
}
