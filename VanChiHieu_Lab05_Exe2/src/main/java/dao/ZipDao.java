package dao;

import org.bson.Document;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
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
}
