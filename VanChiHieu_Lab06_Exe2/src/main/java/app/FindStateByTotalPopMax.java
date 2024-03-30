package app;

import dao.ZipDao;

public class FindStateByTotalPopMax {
	public static void main(String[] args) {
		ZipDao zipDao = new ZipDao();
		zipDao.findStateByTotalPopMax().forEach((k, v) -> {
			System.out.println(k + " " + v);
		});
	}
}
