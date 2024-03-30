package app;

import dao.ZipDao;

public class FindCityByPop {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ZipDao zipDao = new ZipDao();
		zipDao.findCityByPop(10, 50).forEach(System.out::println);
	}
}
