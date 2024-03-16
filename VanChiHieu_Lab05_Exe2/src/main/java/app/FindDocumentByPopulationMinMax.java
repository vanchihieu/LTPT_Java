package app;

import dao.ZipDao;
import db.Connection;

public class FindDocumentByPopulationMinMax {
	public static void main(String[] args) {
		// 16.Tìm các document có dân số lớn (nhỏ) nhất
		ZipDao zipDao = new ZipDao(new Connection().getDatabase());
		
		zipDao.findDocumentByPopulationMax();
	}
}
