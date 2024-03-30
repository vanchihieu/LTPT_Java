package app;

import org.bson.types.ObjectId;

import dao.ZipDao;
import entities.Location;
import entities.Zip;

public class InsertDocument {
	public static void main(String[] args) {
		Zip zip = new Zip();
		zip.setId(new ObjectId());
		zip.setCity("Nhan");
		zip.setZip("35019");
		zip.setLoc(new Location(33, 86));
		zip.setPop(3232);
		zip.setState("AL");
		
		ZipDao zipDao = new ZipDao();
		
		zipDao.insertDocument(zip);
	}
}
