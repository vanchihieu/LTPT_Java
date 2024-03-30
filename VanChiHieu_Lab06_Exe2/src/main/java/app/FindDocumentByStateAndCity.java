package app;

import dao.ZipDao;

public class FindDocumentByStateAndCity {
	public static void main(String[] args) {
		ZipDao zipDao = new ZipDao();
		zipDao.findDocumentByStateAndCity("CT", "WATERBURY").forEach(System.out::println);
	}
}
