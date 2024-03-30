package app;

import dao.ZipDao;

public class FindDistinctState {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ZipDao zipDao = new ZipDao();
		zipDao.findDistinctState().forEach(System.out::println);
	}
}
