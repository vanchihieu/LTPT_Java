package app;

import java.util.List;

import dao.RestaurantDao;
import entities.Restaurant;

public class SearchRestaurant {
	public static void main(String[] args) {
		RestaurantDao  dao = new RestaurantDao();
		List<Restaurant> result = dao.searchRestaurant("Brooklyn");
		result.forEach(System.out::println);
	}
}
