package app;

import dao.ZipDao;
import db.Connection;

public class CountCityByState {
	public static void main(String[] args) {
		// 13.Bang WA có bao nhiêu city (nếu trùng chỉ tính 1 lần)
		ZipDao zipDao = new ZipDao(new Connection().getDatabase());
		
		zipDao.countCityByState("WA");
	}
}
