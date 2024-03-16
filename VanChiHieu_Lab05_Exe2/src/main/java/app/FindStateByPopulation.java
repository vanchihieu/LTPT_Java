package app;

import dao.ZipDao;
import db.Connection;

public class FindStateByPopulation {
	public static void main(String[] args) {
		// 10.Tìm tất cả các bang mà có chứa ít nhất 1 thành phố có dân số trên 100000
		ZipDao zipDao = new ZipDao(new Connection().getDatabase());
		
		zipDao.findStateByPopulation(100000);
	}
}
