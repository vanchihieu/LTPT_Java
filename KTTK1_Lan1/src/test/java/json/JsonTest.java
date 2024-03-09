package json;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import entities.Restaurant;
import objectmodelapi.RestaurantListChecker;

public class JsonTest {
	@Test
	@DisplayName("testValidRestaurantList")
	public void testValidRestaurantList() {
		// Tạo danh sách các nhà hàng hợp lệ
		List<Restaurant> restaurantList = new ArrayList<>();
		restaurantList.add(new Restaurant("Restaurant 1", "Manhattan"));
		restaurantList.add(new Restaurant("Restaurant 2", "Brooklyn"));

		// Kiểm tra danh sách nhà hàng
		boolean isRestaurantListValid = RestaurantListChecker.checkRestaurantList(restaurantList);

		// Kiểm tra kết quả
		Assert.assertTrue(isRestaurantListValid);
	}

	@Test
	public void testInvalidRestaurantList_Size() {
		// Tạo danh sách các nhà hàng không hợp lệ (số lượng khác 2)
		List<Restaurant> restaurantList = new ArrayList<>();
		restaurantList.add(new Restaurant("Restaurant 1", "Manhattan"));

		// Kiểm tra danh sách nhà hàng
		boolean isRestaurantListValid = RestaurantListChecker.checkRestaurantList(restaurantList);

		// Kiểm tra kết quả
		Assert.assertFalse(isRestaurantListValid);
	}

	@Test
	public void testInvalidRestaurantList_Borough() {
		// Tạo danh sách các nhà hàng không hợp lệ (thuộc tính borough không đúng)
		List<Restaurant> restaurantList = new ArrayList<>();
		restaurantList.add(new Restaurant("Restaurant 1", "Manhattan"));
		restaurantList.add(new Restaurant("Restaurant 2", "Queens"));

		// Kiểm tra danh sách nhà hàng
		boolean isRestaurantListValid = RestaurantListChecker.checkRestaurantList(restaurantList);

		// Kiểm tra kết quả
		Assert.assertFalse(isRestaurantListValid);
	}
}
