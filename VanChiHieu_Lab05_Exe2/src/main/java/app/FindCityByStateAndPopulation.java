package app;

import dao.ZipDao;
import db.Connection;

public class FindCityByStateAndPopulation {
	public static void main(String[] args) {
		// 8. Tìm tất cả các thành phố của bang MA có dân số trên 500
		ZipDao zipDao = new ZipDao(new Connection().getDatabase());
		
		zipDao.findCityByStateAndPopulation("MA", 500);
	}
}
