package app;

import dao.ZipDao;
import db.Connection;

public class FindStateByTotalPopulation {
	public static void main(String[] args) {
		// 15.Tìm tất cả các bang có tổng dân số trên 10000000
		ZipDao zipDao = new ZipDao(new Connection().getDatabase());
		
		zipDao.findStateByTotalPopulation(10000000);
	}
}
