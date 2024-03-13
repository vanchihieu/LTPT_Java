package app;

import dao.ZipDao;
import db.Connection;

public class FindPopulationOfCity {
	public static void main(String[] args) {
		// 6. Tìm dân số của thành phố FISHERS ISLAND
		ZipDao zipDao = new ZipDao(new Connection().getDatabase());

		zipDao.findPopulationOfCity("FISHERS ISLAND");
	}
}
