package demo;

import org.bson.types.ObjectId;

import dao.ZipDao;
import entity.Location;
import entity.Zip;

public class UpdateZip {
	public static void main(String[] args) throws InterruptedException {
		ZipDao dao = new ZipDao();
		Zip zip = new Zip();
		zip.setId(new ObjectId("5c8eccc1caa187d17ca6ed2f"));
        zip.setCity("Hung Yen");
        zip.setZip("8714");
        
        Location loc = new Location();
        loc.setY(3000);
        loc.setX(3000);
        zip.setLocation(loc);

        zip.setPop(1111);
        zip.setState("HY");
        
        boolean rs = dao.updateZipById(zip);
        System.out.println(rs);
	
	}
}
