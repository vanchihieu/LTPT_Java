package test;



import java.util.List;

import org.bson.Document;
import static org.junit.Assert.assertEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import dao.RestaurantDao;

public class RestauranttDaoTest {
	private static RestaurantDao restaurantDao;

	@BeforeAll
	static void setup() {
		restaurantDao = new RestaurantDao();
	}

	@Test
	public void getTop5Restaurant() {
		List<Document> result = restaurantDao.getTop5Restaurant();
		assertEquals(5, result.size());
		
	}

//	@Test
//	public void getRestaurants() {
//		restaurantDao.getRestaurants();
//		assertEquals(25359, restaurantDao.getRestaurants().size());
//	}
	
	@Test
	public void updateAddress() {
		boolean result = restaurantDao.updateAddress("40356068", "Tov Kosher Kitchen", "Chi Hieu4");
		assertEquals(true, result);
	}
	
	@Test
	public void deleteRestaurant() {
		boolean result = restaurantDao.deleteRestaurant("40356068");
		assertEquals(true, result);
	}
}
