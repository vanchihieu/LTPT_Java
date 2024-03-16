package dao;

import java.util.ArrayList;
import java.util.Arrays;

import org.bson.Document;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Accumulators;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.result.UpdateResult;

public class ZipDao {
	private MongoCollection<Document> zipCollection;

	public ZipDao(MongoDatabase db) {
		this.zipCollection = db.getCollection("zips");
	}

	// 1. Hiển thị n documents từ document thứ k.
	public void displayDocuments(int k, int n) {
		zipCollection.find().skip(k).limit(n).forEach(doc -> {
			System.out.println(doc.toJson());
		});
	}

	// 2. Chèn thêm 1 document mới
	public void insertDocument(Document doc) {
		zipCollection.insertOne(doc);
	}

	// 3. Cập nhật thông tin của một document khi biết id
	// db.zips.updateOne({_id:null},{$set:{state:'Hieu'}})
	public boolean updateDocument(String id, Document doc) {
		UpdateResult kq = zipCollection.updateOne(new Document("_id", id), doc);

		return kq.getModifiedCount() > 0 ? true : false;
	}

	// 4. Tìm các document có city là PALMER
	// db.zips.find({city:'PALMER'})
	public void findDocumentByCity(String city) {
		zipCollection.find(new Document("city", city)).forEach(doc -> {
			System.out.println(doc.toJson());
		});
	}

	// 5. Tìm các document có dân số >100000
	// db.zips.find({pop:{$gt:100000}})
	public void findDocumentByPopulation(int population) {
		zipCollection.find(new Document("pop", new Document("$gt", population))).forEach(doc -> {
			System.out.println(doc.toJson());
		});
	}

	// 6. Tìm dân số của thành phố FISHERS ISLAND
	// db.zips.find({city:'FISHERS ISLAND'}, {pop:1, _id:0} )
	public void findPopulationOfCity(String city) {
		zipCollection.find(new Document("city", city)).projection(new Document("pop", 1).append("_id", 0))
				.forEach(doc -> {
					System.out.println(doc.toJson());
				});
	}

	// 7. Tìm các thành phố có dân số từ 10 – 50
	// db.zips.find({pop:{$gte:10000, $lte:50000}})
	public void findCityByPopulation(int min, int max) {
		zipCollection.find(new Document("pop", new Document("$gte", min).append("$lte", max))).forEach(doc -> {
			System.out.println(doc.toJson());
		});
	}

	// 8. Tìm tất cả các thành phố của bang MA có dân số trên 500
	// db.zips.find({state:'MA', pop:{$gt:500}})
	public void findCityByStateAndPopulation(String state, int population) {
		zipCollection.find(new Document("state", state).append("pop", new Document("$gt", population))).forEach(doc -> {
			System.out.println(doc.toJson());
		});
	}

	// 9. Tìm tất cả các bang (không trùng)
	// db.zips.distinct('state')
	public void findDistinctState() {
		zipCollection.distinct("state", String.class).forEach(state -> {
			System.out.println(state);
		});
	}

	// 10.Tìm tất cả các bang mà có chứa ít nhất 1 thành phố có dân số trên 100000
	// db.zips.distinct("state", { "pop": { $gt: 100000 } })
	public void findStateByPopulation(int population) {
		zipCollection.distinct("state", new Document("pop", new Document("$gt", population)), String.class)
				.forEach(state -> {
					System.out.println(state);
				});
	}

	// 11.Tính dân số trung bình của mỗi bang
	// db.zips.aggregate([{$group:{_id:"$state", avgPop:{$avg:"$pop"}}}])
	public void averagePopulationByState() {
		zipCollection.aggregate(Arrays.asList(Aggregates.group("$state", Accumulators.avg("avgPop", "$pop"))))
				.forEach(doc -> {
					System.out.println(doc.toJson());
				});
	}

	// 12.Tìm những document của bang 'CT' và thành phố 'WATERBURY'
	// db.zips.find({state:'CT', city:'WATERBURY'})
	public void findDocumentByStateAndCity(String state, String city) {
		zipCollection.find(new Document("state", state).append("city", city)).forEach(doc -> {
			System.out.println(doc.toJson());
		});
	}

	// 13.Bang WA có bao nhiêu city (nếu trùng chỉ tính 1 lần)
	// db.zips.distinct('city', {state:'WA'}).length
	public void countCityByState(String state) {
		System.out.println(zipCollection.distinct("city", new Document("state", state), String.class)
				.into(new ArrayList<>()).size());
	}

	// 14.Tính số city của mỗi bang (nếu trùng chỉ tính 1 lần), kết quả giảm dần
	// theo số city
	// db.zips.aggregate([{$group:{_id:"$state", count:{$sum:1}}},{$sort:{count:-1}}])
	public void countCityByState() {
		zipCollection.aggregate(Arrays.asList(Aggregates.group("$state", Accumulators.sum("count", 1)),
				Aggregates.sort(new Document("count", -1)))).forEach(doc -> {
					System.out.println(doc.toJson());
				});
	}

	// 15.Tìm tất cả các bang có tổng dân số trên 10000000
	// db.zips.aggregate([{$group:{_id:"$state",
	// totalPop:{$sum:"$pop"}}},{$match:{totalPop:{$gt:10000000}}}])
	public void findStateByTotalPopulation(int population) {
		zipCollection.aggregate(Arrays.asList(Aggregates.group("$state", Accumulators.sum("totalPop", "$pop")),
				Aggregates.match(new Document("totalPop", new Document("$gt", population))))).forEach(doc -> {
					System.out.println(doc.toJson());
				});
	}
	
	// 16.Tìm các document có dân số lớn nhất
	// db.zips.find().sort({pop:-1}).limit(1)
	public void findDocumentByPopulationMax() {
		zipCollection.find().sort(new Document("pop", -1)).limit(1).forEach(doc -> {
			System.out.println(doc.toJson());
		});
	}
	
	
	
	// 17.Tìm bang có tổng dân số lớn nhất
	// db.zips.aggregate([{$group:{_id:"$state", totalPop:{$sum:"$pop"}}},{$sort:{totalPop:-1}},{$limit:1}])
	public void findStateByTotalPopulationMax() {
        zipCollection.aggregate(Arrays.asList(Aggregates.group("$state", Accumulators.sum("totalPop", "$pop")),
                Aggregates.sort(new Document("totalPop", -1)), Aggregates.limit(1))).forEach(doc -> {
                    System.out.println(doc.toJson());
                });
    }
	
	// 18.Xuất những document có dân số dưới dân số trung bình của mỗi city
	// db.zips.aggregate([{$group:{_id:{state:"$state", city:"$city"}, avgPop:{$avg:"$pop"}}},{$lookup:{from:"zips", localField:"_id.city", foreignField:"city", as:"zips"}},{$unwind:"$zips"},{$match:{"zips.pop":{$lt:"$avgPop"}}},{$project:{"zips._id":1, "zips.city":1, "zips.state":1, "zips.pop":1}}])
	public void findDocumentByPopulationLessThanAverage() {
		zipCollection.aggregate(Arrays.asList(
				Aggregates.group(new Document("state", "$state").append("city", "$city"),
						Accumulators.avg("avgPop", "$pop")),
				Aggregates.lookup("zips", "_id.city", "city", "zips"), Aggregates.unwind("$zips"),
				Aggregates.match(new Document("zips.pop", new Document("$lt", "$avgPop"))),
				Aggregates.project(new Document("zips._id", 1).append("zips.city", 1).append("zips.state", 1)
						.append("zips.pop", 1))))
				.forEach(doc -> {
					System.out.println(doc.toJson());
				});
	}
	
	// 19.Dựa vào tập kết quả câu trên, xóa các documents khi biết city
	// db.zips.deleteMany({city:'WATERBURY'})
	public boolean deleteDocumentByCity(String city) {
		zipCollection.deleteMany(new Document("city", city));
		return true;
	}

}