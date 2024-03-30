package app;

import dao.RestaurantDao;

public class DeleteRestaurant {
	public static void main(String[] args) {
		RestaurantDao  dao = new RestaurantDao();
		boolean result = dao.deleteRestaurant("40356068");
		System.out.println(result);
	}
}
