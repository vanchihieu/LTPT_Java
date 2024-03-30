package demo;

import org.bson.types.ObjectId;

import dao.ZipDao;

public class DeleteZip {

	public static void main(String[] args) throws InterruptedException {
		ZipDao zipDao = new ZipDao();
		
		boolean result = zipDao.deleteZipById(new ObjectId("5c8eccc1caa187d17ca6ed1e"));
		System.out.println(result);
	}

}
