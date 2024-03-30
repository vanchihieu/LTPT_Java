package app;

import dao.RestaurantDao;

public class GetRestaurants {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RestaurantDao dao = new RestaurantDao();
		dao.getRestaurants().forEach(System.out::println);
		
//		dao.getRestaurants("Manhattan", "Sandwiches").forEach(System.out::println);
	}
}
