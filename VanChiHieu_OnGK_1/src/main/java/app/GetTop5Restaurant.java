package app;

import java.util.List;

import org.bson.Document;

import dao.RestaurantDao;
import entities.Restaurant;

public class GetTop5Restaurant {
	public static void main(String[] args) {
		RestaurantDao dao = new RestaurantDao();
		// list
//		List<Restaurant> products = dao.getTop5Restaurant2();
//		products.forEach(p -> System.out.println(p));
		
		// document
		dao.getTop5Restaurant().forEach((Document d) -> {
			System.out.println(d.toJson());
		});
	}
}
