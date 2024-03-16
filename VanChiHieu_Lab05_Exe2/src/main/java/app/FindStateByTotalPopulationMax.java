package app;

import dao.ZipDao;
import db.Connection;

public class FindStateByTotalPopulationMax {
	public static void main(String[] args) {
		// 17.Tìm bang có tổng dân số lớn nhất
		ZipDao zipDao = new ZipDao(new Connection().getDatabase());
		
		zipDao.findStateByTotalPopulationMax();
	}
}
