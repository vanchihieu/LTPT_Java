package app;

import dao.ZipDao;
import db.Connection;

public class FindDocumentByPopulation {
	public static void main(String[] args) {
		// 2. Tìm các document có population lớn hơn 100000
		ZipDao zipDao = new ZipDao(new Connection().getDatabase());

		zipDao.findDocumentByPopulation(100000);
	}
}
