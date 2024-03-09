package json;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import entities.Address;
import entities.Grade;
import entities.Restaurant;

public class JsonTest {
	@Test
	 public void testFirstRestaurantName() {
		Address address = new Address();
		address.setBuilding("1007");
		List<Double> coord = new ArrayList<>();
		coord.add(-73.856077);
		coord.add(40.848447);
		address.setCoord(coord);
		address.setStreet("Morris Park Ave");
		address.setZipcode("10462");

		Grade grade1 = new Grade();
		grade1.setDate(1393804800000L);
		grade1.setGrade("A");
		grade1.setScore(2);

		Grade grade2 = new Grade();
		grade2.setDate(1378857600000L);
		grade2.setGrade("A");
		grade2.setScore(6);

		Grade grade3 = new Grade();
		grade3.setDate(1358985600000L);
		grade3.setGrade("A");
		grade3.setScore(10);

		List<Grade> grades = new ArrayList<>();
		grades.add(grade1);
		grades.add(grade2);
		grades.add(grade3);

		Restaurant restaurant = new Restaurant();
		restaurant.setRestaurantId(30000001);
		restaurant.setName("Morris Park Bake Shop");
		restaurant.setBorough("Bronx");
		restaurant.setCuisine("Bakery");
		restaurant.setActive(false);
		List<String> categories = new ArrayList<>();
		categories.add("Seafood");
		categories.add("Fastfood");
		restaurant.setCategories(categories);
		restaurant.setAddress(address);
		restaurant.setGrades(grades);
		
		 // Kiểm tra xem tên nhà hàng đầu tiên có phải là "Hieu" hay không
        Assert.assertEquals("Hieu", restaurant.getName());
	}
}
