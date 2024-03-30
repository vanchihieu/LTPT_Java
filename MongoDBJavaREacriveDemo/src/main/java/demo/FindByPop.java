package demo;

import java.util.List;

import dao.ZipDao;
import entity.Zip;

public class FindByPop {
	public static void main(String[] args) throws InterruptedException {
		ZipDao zipDao = new ZipDao();
		List<Zip> zips = zipDao.findByPop(100000);
		zips.forEach(zip -> System.out.println(zip));
	}
}
