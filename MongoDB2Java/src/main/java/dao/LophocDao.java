package dao;

import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Accumulators;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.InsertOneResult;

import entity.Lophoc;

import static com.mongodb.MongoClientSettings.getDefaultCodecRegistry;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.bson.Document;
import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

public class LophocDao extends AbstractDao {
	private MongoCollection<Lophoc> lophocCollection;

	public LophocDao(MongoClient client) {
		super(client);

//		CodecProvider pojoCodecProvider = PojoCodecProvider.builder().automatic(true).build();
//		CodecRegistry pojoCodecRegistry = fromRegistries(getDefaultCodecRegistry(), fromProviders(pojoCodecProvider));

		CodecRegistry pojoProvider = CodecRegistries.fromProviders(PojoCodecProvider.builder().automatic(true).build());
		CodecRegistry setting = MongoClientSettings.getDefaultCodecRegistry();
		CodecRegistry pojoCodeRegistry = CodecRegistries.fromRegistries(setting, pojoProvider);

		lophocCollection = db.getCollection("dsLopHoc1", Lophoc.class).withCodecRegistry(pojoCodeRegistry);
	}

	public boolean themLophoc(Lophoc lh) {
		InsertOneResult kq = lophocCollection.insertOne(lh);
		return kq.getInsertedId() != null ? true : false;
	}

	// db.dsLophoc.find({mslop: {$regex:/.*CLC$/i } })
//	 db.dsLophoc.find({mslop: {$regex:'.*CLC$', $options: 'i' } })
	public List<Lophoc> getDSLophocCLC() {
		List<Lophoc> dslh = new ArrayList<>();

		Document doc = new Document("mslop", new Document("$regex", ".*CLC$").append("$options", "i"));

		lophocCollection.find(doc)
//			.find(Filters.regex("mslop", ".*CLC$", "i"))
				.iterator().forEachRemaining(dslh::add);

		return dslh;
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
		
		Lophoc doc = lophocCollection.aggregate(Arrays.asList(
				Aggregates.group("$malop", Accumulators.sum("sosv", 1))
				)).first();
		System.out.println(doc);
		
		return map;
	}
}
