package app;

import dao.ZipDao;

public class FindDocumentByCity {
	public static void main(String[] args) {
		ZipDao zipDao = new ZipDao();
		zipDao.findDocumentByCity("PALMER").forEach(System.out::println);
	}
}
