package app;

import org.bson.Document;

import dao.ZipDao;
import db.Connection;

public class UpdateDocument {
	public static void main(String[] args) {
		// 3. Cập nhật thông tin của một document khi biết id
		ZipDao zipDao = new ZipDao(new Connection().getDatabase());
		
		zipDao.updateDocument("null", new Document("$set", new Document("zip", 12)));
		System.out.println("Cập nhật thành công!");
	}
}
