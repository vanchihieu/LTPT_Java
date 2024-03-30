package demo;

import org.bson.types.ObjectId;

import dao.ZipDao;
import entity.Zip;

public class FindByIDTask {
	public static void main(String[] args) {
		ZipDao zipDao = new ZipDao();
		Zip zip = zipDao.findById(new ObjectId("5c8eccc1caa187d17ca6ed19"));
		System.out.println(zip);
	}
}
