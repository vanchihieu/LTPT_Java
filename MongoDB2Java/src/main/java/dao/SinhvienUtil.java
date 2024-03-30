package dao;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.bson.Document;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Accumulators;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Sorts;

import entity.Lophoc;

public class SinhvienUtil extends AbstractDao {
	private MongoCollection<Document> sinhvienCollection;

	public SinhvienUtil(MongoClient client) {
		super(client);
		sinhvienCollection = db.getCollection("dsSinhvien");
	}

//	db.dsSinhvien.aggregate([
//	{$group: {_id: '$malop', sosv:{$count:{}}}}, 
//	{$group: {_id: '$malop', sosv:{$sum:1 }}}, 

//	{$sort:{sosv: -1}}, 
//	{$limit:1}, 
//	{$lookup: {from: 'dsLophoc', localField: '_id', foreignField: 'mslop', as:'lh'} },
//	{$unwind: '$lh'}, 
//	{$project:{_id:0, sosv:1, 'lh.mslop': 1, 'lh.sisoDukien': 1}} ])
	public Map<Lophoc, Integer> getLophocSisoMax() {
//		Map<Lophoc, Integer> map = new TreeMap<Lophoc, Integer>(new Comparator<Lophoc>() {
//
//			@Override
//			public int compare(Lophoc o1, Lophoc o2) {
//				return Integer.valueOf(o1.getSisoDukien()).compareTo(Integer.valueOf(o2.getSisoDukien()));
//
//			}
//		});

		Map<Lophoc, Integer> map = new HashMap<Lophoc, Integer>();
//		Map<Lophoc, Integer> map = new TreeMap<Lophoc, Integer>();

		Document doc = sinhvienCollection
				.aggregate(Arrays.asList(
						Aggregates.group("$malop", Accumulators.sum("sosv", 1)),
						Aggregates.sort(Sorts.descending("sosv")), Aggregates.limit(1),
						Aggregates.lookup("dsLophoc", "_id", "mslop", "lh"), Aggregates.unwind("$lh")))
				.first();
//		sinhvienCollection
//		.aggregate(Arrays.asList(Aggregates.group("$malop", Accumulators.sum("sosv", 1)),
//				Aggregates.sort(Sorts.descending("sosv")), Aggregates.limit(1),
//				Aggregates.lookup("dsLophoc", "_id", "mslop", "lh"), Aggregates.unwind("$lh")))
//		.iterator().forEachRemaining(doc -> System.out.println(doc));

		Document lhDoc = (Document) doc.get("lh");
		
		map.put(new Lophoc(lhDoc.getString("mslop"), lhDoc.getString("tenlop"), lhDoc.getInteger("sisoDukien")),
				doc.getInteger("sosv"));

		return map;
	}
}
