package demo;

import java.util.List;

import dao.ZipDao;
import entity.Zip;

public class FindByCity {
	public static void main(String[] args) throws InterruptedException {
		ZipDao zipDao = new ZipDao();
		
		List<Zip> listOfCity = zipDao.findCity("PALMER");
		listOfCity.forEach(list -> System.out.println(list));
	}
}
