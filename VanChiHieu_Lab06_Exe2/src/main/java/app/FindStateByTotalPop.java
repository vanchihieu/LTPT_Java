package app;

import dao.ZipDao;

public class FindStateByTotalPop {
	public static void main(String[] args) {
		ZipDao zipDao = new ZipDao();
		zipDao.findStateByTotalPop(10000000).forEach((k, v) -> {
			System.out.println(k + " " + v);
		});
	}
}
