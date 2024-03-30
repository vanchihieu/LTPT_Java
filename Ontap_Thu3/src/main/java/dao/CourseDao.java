package dao;

import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistry;
import static org.bson.codecs.configuration.CodecRegistries.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.conversions.Bson;
import org.reactivestreams.Publisher;

import com.mongodb.MongoClientSettings;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Indexes;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.UpdateResult;
import com.mongodb.reactivestreams.client.AggregatePublisher;
import com.mongodb.reactivestreams.client.FindPublisher;
import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoCollection;
import com.mongodb.reactivestreams.client.MongoDatabase;

import db.Connection;
import entity.Course;
import entity.Instructor;

public class CourseDao {
	private MongoCollection<Course> courseColl;
	private MongoCollection<Document> docColl;

	public CourseDao() {
		MongoClient client = Connection.getInstance().getClient();
		MongoDatabase db = client.getDatabase("Bac20111041");
		CodecRegistry pojoCodecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),
				fromProviders(PojoCodecProvider.builder().automatic(true).build()));
		courseColl = db.getCollection("courses", Course.class).withCodecRegistry(pojoCodecRegistry);
		docColl = db.getCollection("courses");
	}

//	db.course.createIndex({name: "text", description: "text"})
	public List<Course> getCourses(String keywords) {
		Bson keys = Indexes.compoundIndex(Indexes.text("name"), Indexes.text("description"));
		Publisher<String> index = courseColl.createIndex(keys);
		CourseSubcriber<String> subIndex = new CourseSubcriber<>();
		index.subscribe(subIndex);
		FindPublisher<Course> list = courseColl.find(Filters.text(keywords));
		CourseSubcriber<Course> sub = new CourseSubcriber<>();
		list.subscribe(sub);
		return sub.getResults();
	}

//db.courses.aggregate([{$unwind:'$sections'}
//	,{$match:{"sections.instructor.id":"I015"}}
//	,{$project:{"sections.instructor":1,_id:0}}
//	,{$replaceWith:'$sections'}])
	public Instructor getInstructor(String id) {
		AggregatePublisher<Document> rs = docColl.aggregate(Arrays.asList(Document.parse("{$unwind:'$sections'}"),
				Document.parse("{$match:{\"sections.instructor.id\":\"" + id + "\"}}"),
				Document.parse("{$project:{\"sections.instructor\":1,_id:0}}"),
				Document.parse("{$replaceWith:'$sections'}")));
		CourseSubcriber<Document> sub = new CourseSubcriber<>();
		rs.subscribe(sub);
		Document ans = sub.getSingleResult();
		Document doc = (Document) ans.get("instructor");
		Instructor instructor = new Instructor(doc.getString("id"), doc.getString("name"), doc.getString("email"),
				doc.getString("phone"));
		return instructor;
	}

//	db.courses.aggregate([{$unwind:'$sections'}
//	,{$group:{_id:'$sections.instructor.id',count:{$sum:1}}}])
//	,{$sort:{"sections.instructor.name":1}}
	public Map<Instructor, Integer> getNumberOfCourseByInstructor() {
		Map<Instructor, Integer> map = new HashMap<>();
		AggregatePublisher<Document> pub = docColl.aggregate(Arrays.asList(Document.parse("{$unwind:'$sections'}"),
				Document.parse("{$group:{_id:'$sections.instructor.id',count:{$sum:1}}}"),
				Document.parse("{$sort:{\"sections.instructor.name\":1}}")));
		CourseSubcriber<Document> sub = new CourseSubcriber<>();
		pub.subscribe(sub);
		List<Document> docs = sub.getResults();
		for (Document doc : docs) {
			Instructor i = getInstructor(doc.getString("_id"));
//			 System.out.println(i);
			int count = doc.getInteger("count");
			map.put(i, count);
		}
		return map;
	}

	public boolean updateTheRoomOfSection(String courseId, String sectionNo, String newRoom) {
		Bson filter = Filters.and(Filters.eq("_id", courseId), Filters.eq("sections.sectionNo", sectionNo));
		Bson update = Updates.set("sections.$.room", newRoom);
		Publisher<UpdateResult> pub = courseColl.updateOne(filter, update);
		CourseSubcriber<UpdateResult> sub = new CourseSubcriber<>();
		pub.subscribe(sub);
		return sub.getSingleResult().getModifiedCount() > 0;
	}

}
