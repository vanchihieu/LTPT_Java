package dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bson.Document;

import com.mongodb.MongoClientException;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;

import entity.Sinhvien;

public class SinhvienDao extends AbstractDao {
	private MongoCollection<Document> sinhvienCollection;

	public SinhvienDao(MongoClient client) {
		super(client);
		sinhvienCollection = db.getCollection("dsSinhvien1");
	}

	// db.dsSinhvien1.updateOne({_id:'845'},{$set:{tuoi:22}})
	public boolean capnhapTuoiSV(String mssv, int tuoi) {

		Document fillter = new Document("_id", mssv);
		Document update = new Document("$set", new Document("tuoi", tuoi));
		UpdateResult kq = sinhvienCollection.updateOne(fillter, update);

		return kq.getModifiedCount() > 0 ? true : false;
	}

	// db.dsSinhvien1.updateOne({_id:'845'},{$set:{tuoi:22}})
	public boolean capnhapTuoiSV2(String mssv, int tuoi) {

		UpdateResult kq = sinhvienCollection.updateOne(Filters.eq("_id", mssv), Updates.set("tuoi", tuoi));

		return kq.getModifiedCount() > 0 ? true : false;
	}

	/**
	 * cap nhat thong tin cua sinh vien
	 * 
	 * @param sv
	 * @return
	 */
	public void capnhatSinhvien(Sinhvien sv) {
		Document doc = chuyenDocument(sv);

		sinhvienCollection.findOneAndReplace(Filters.eq("_id", sv.getMssv()), doc);

	}

	public boolean xoaSinhvienByMssv(String mssv) {
		DeleteResult kq = sinhvienCollection.deleteOne(Filters.eq("_id", mssv));

		return kq.getDeletedCount() > 0 ? true : false;
	}

	public List<Sinhvien> getDSinhvien(int skip, int limit) {
		List<Sinhvien> dssv = new ArrayList<Sinhvien>();

		 sinhvienCollection
				.find()
				.skip(skip)
				.limit(limit)
				.iterator()
				.forEachRemaining(doc -> {
					Sinhvien sv = chuyenSinhvien(doc);
					dssv.add(sv);
				});

//		MongoCursor<Document> it = docs.iterator();
//		while (it.hasNext()) {
//			Document doc = it.next();
//			Sinhvien sv = chuyenSinhvien(doc);
//
//			dssv.add(sv);
//		}
		return dssv;
	}

//	  { _id: '987', ho_sv: 'Be', ten: 'Em', tuoi: 18 }
	private Sinhvien chuyenSinhvien(Document doc) {
		Sinhvien sv = new Sinhvien();

		sv.setMssv(doc.getString("_id"));
		sv.setHo(doc.getString("ho_sv"));
		sv.setTen(doc.getString("ten"));
		sv.setTuoi(doc.getInteger("tuoi"));

		return sv;
	}

	/**
	 * 
	 * @param dssv
	 */
	public void themDSSinhvien(List<Sinhvien> dssv) {
		List<Document> docs = new ArrayList<Document>();
		try {

			dssv.forEach(sv -> {
				Document doc = chuyenDocument(sv);

				docs.add(doc);
			});

			sinhvienCollection.insertMany(docs);

		} catch (MongoClientException e) {
			e.printStackTrace();
		}
	}

	private Document chuyenDocument(Sinhvien sv) {
		Document doc = new Document().append("_id", sv.getMssv()).append("ho_sv", sv.getHo()).append("ten", sv.getTen())
				.append("tuoi", sv.getTuoi());
		return doc;
	}

	/**
	 * Thêm một sinh viên
	 * 
	 * @param sv
	 * @return
	 */
	public Sinhvien themSinhvien(Sinhvien sv) {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("mssv", sv.getMssv());
			map.put("ho", sv.getHo());
			map.put("ten", sv.getTen());
			map.put("tuoi", sv.getTuoi());

			Document doc = new Document(map);

			sinhvienCollection.insertOne(doc);

			return sv;
		} catch (MongoClientException e) {
			e.printStackTrace();
		}
		return null;

	}

}
