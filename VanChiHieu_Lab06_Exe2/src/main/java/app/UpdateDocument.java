package app;

import dao.ZipDao;

public class UpdateDocument {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ZipDao zipDao = new ZipDao();
		zipDao.updateDocument("5c8eccc1caa187d17ca6ed28", "Hieu");
	}
}
