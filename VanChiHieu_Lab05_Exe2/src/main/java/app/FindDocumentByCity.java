package app;

import dao.ZipDao;
import db.Connection;

public class FindDocumentByCity {
	public static void main(String[] args) {
		// 4. Tìm các document có city là PALMER
		ZipDao zipDao = new ZipDao(new Connection().getDatabase());

		zipDao.findDocumentByCity("PALMER");
	}
}
