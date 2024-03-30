package app;

import dao.ZipDao;

public class FindCityByStateAndPop {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ZipDao zipDao = new ZipDao();
		zipDao.findCityByStateAndPop("MA", 500).forEach(System.out::println);
	}
}
