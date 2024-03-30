package demo;

import dao.ZipDao;

public class FindCityByPop {
	public static void main(String[] args) {
		ZipDao zipDao = new ZipDao();
		zipDao.getCityByPop()
		.entrySet()
		.forEach(x -> System.out.println(x));
	}
}
