package objectmodelapi;

import java.util.List;

import entities.Restaurant;

public class RestaurantListChecker {
	public static boolean checkRestaurantList(List<Restaurant> restaurantList) {
		if (restaurantList.size() != 2) {
			return false;
		}

		Restaurant secondRestaurant = restaurantList.get(1);
		String borough = secondRestaurant.getBorough();

		return borough.equals("Brooklyn");
	}
}
