package app;

import dao.ZipDao;
import db.Connection;

public class FindDocumentByStateAndCity {
	public static void main(String[] args) {
		// 12.Tìm những document của bang 'CT' và thành phố 'WATERBURY'
		ZipDao zipDao = new ZipDao(new Connection().getDatabase());
		
		zipDao.findDocumentByStateAndCity("CT", "WATERBURY");
	}
}
