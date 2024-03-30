package app;

import dao.ZipDao;

public class FindDocumentByPop {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ZipDao zipDao = new ZipDao();
		zipDao.findDocumentByPop(1000).forEach(System.out::println);
	}
}
