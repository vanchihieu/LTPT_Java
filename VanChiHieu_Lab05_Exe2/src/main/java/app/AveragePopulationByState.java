package app;

import dao.ZipDao;
import db.Connection;

public class AveragePopulationByState {
	public static void main(String[] args) {
		// 11.Tính dân số trung bình của mỗi bang
		ZipDao zipDao = new ZipDao(new Connection().getDatabase());
		
		zipDao.averagePopulationByState();
	}
}
