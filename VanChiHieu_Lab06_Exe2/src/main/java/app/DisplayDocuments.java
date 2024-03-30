package app;

import dao.ZipDao;

public class DisplayDocuments {
	public static void main(String[] args) {
		ZipDao zipDao = new ZipDao();
		zipDao.displayDocuments(10, 5).forEach(System.out::println);
	}
}
