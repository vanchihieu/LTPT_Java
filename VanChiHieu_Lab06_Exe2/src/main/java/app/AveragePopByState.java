package app;

import dao.ZipDao;

public class AveragePopByState {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ZipDao zipDao = new ZipDao();
		zipDao.averagePopByState().forEach((k,v) -> {
            System.out.println(k + " : " + v);
        });
	}
}
