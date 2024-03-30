package app;

import dao.RestaurantDao;

public class UpdateAddress {
	public static void main(String[] args) {
		RestaurantDao  dao = new RestaurantDao();
		boolean result = dao.updateAddress("40356068", "Tov Kosher Kitchen", "Chi Hieu3");
		System.out.println(result);
	}
}
