package demo;

import dao.ZipDao;

public class GetTotalPopByState {
	public static void main(String[] args) {
		ZipDao zipDao = new ZipDao();
		zipDao.getSumOfPopByState()
		.entrySet()
		.forEach(x -> System.out.println(x));
	}
}
