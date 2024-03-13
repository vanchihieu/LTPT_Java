package app;

import dao.ZipDao;
import db.Connection;

public class FindCityByPopulation {
	public static void main(String[] args) {
		// 7. Tìm các thành phố có dân số từ 10 – 50
		ZipDao zipDao = new ZipDao(new Connection().getDatabase());
		
		zipDao.findCityByPopulation(10, 50);
	}
}
