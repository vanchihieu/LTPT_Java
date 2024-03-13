package app;

import dao.ZipDao;
import db.Connection;

public class DisplayDocuments {
	public static void main(String[] args) {
		// 2. Hiển thị n documents từ document thứ k.
		ZipDao zipDao = new ZipDao(new Connection().getDatabase());
		zipDao.displayDocuments(10, 5);
	}
}
