package app;

import dao.ZipDao;
import db.Connection;

public class FindDistinctState {
	public static void main(String[] args) {
		// 9. Tìm tất cả các bang (không trùng)
		ZipDao zipDao = new ZipDao(new Connection().getDatabase());
		
		zipDao.findDistinctState();
	}
}
