package app;

import dao.ZipDao;

public class FindPopByCity {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ZipDao zipDao = new ZipDao();
		zipDao.findPopByCity("FISHERS ISLAND").forEach(System.out::println);
	}
}
