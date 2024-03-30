package app;

import java.util.List;

import dao.ZipDao;
import entities.Zip;

public class FindDocumentByPopMax {
	public static void main(String[] args) {
		ZipDao zipDao = new ZipDao();
		List<Zip> result = zipDao.findDocumentByPopMax();
		System.out.println(result);
	}
}
