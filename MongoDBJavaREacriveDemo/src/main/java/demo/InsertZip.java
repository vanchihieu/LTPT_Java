package demo;

import org.bson.types.ObjectId;

import dao.ZipDao;
import entity.Location;
import entity.Zip;

public class InsertZip {

	public static void main(String[] args) throws InterruptedException {
		ZipDao dao = new ZipDao();
		Zip zip = new Zip(new ObjectId("5c9eccc1caa117d17ca6ed1e"),
				"Ho Chi Minh", "81414",new Location(33.43427, 414.451),2041, "ASIAN");
		boolean rs = dao.addZip(zip);
		System.out.println(rs);
	}

	

}
