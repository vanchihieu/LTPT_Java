package app;

import org.bson.Document;

import dao.ZipDao;
import dao.ZipDocmentMapper;
import db.Connection;
import entities.Location;
import entities.Zip;

public class InsertDocument {
	public static void main(String[] args) {
		// 2. Chèn thêm 1 document mới
		ZipDao zipDao = new ZipDao(new Connection().getDatabase());
		
		Location loc = new Location(33.331165, 86.208934);
		
		
		Zip zip = new Zip(null, "Sai Gon", "35014", loc, 3062, "Be Em");
		
		ZipDocmentMapper mapper = new ZipDocmentMapper();
		Document doc = mapper.toDocument(zip);
		zipDao.insertDocument(doc);
		
		System.out.println("Insert thành công");
		
		
	}
}
