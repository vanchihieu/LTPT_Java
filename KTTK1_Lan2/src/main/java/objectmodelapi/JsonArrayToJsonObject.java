package objectmodelapi;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import entities.Address;
import entities.Grade;
import entities.Restaurant;
import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;
import jakarta.json.JsonWriter;

public class JsonArrayToJsonObject {
	public static void main(String[] args) {
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

		JsonObjectBuilder addressBuilder = Json.createObjectBuilder();
		addressBuilder.add("building", address.getBuilding()).add("coord", Json.createArrayBuilder(address.getCoord()))
				.add("street", address.getStreet()).add("zipcode", address.getZipcode());

		JsonArrayBuilder gradesBuilder = Json.createArrayBuilder();
		for (Grade grade : restaurant.getGrades()) {
			JsonObjectBuilder gradeBuilder = Json.createObjectBuilder();
			gradeBuilder.add("date", grade.getDate()).add("grade", grade.getGrade()).add("score", grade.getScore());
			gradesBuilder.add(gradeBuilder.build());
		}

		JsonArrayBuilder restaurantBuilder = Json.createArrayBuilder();

		JsonObject restaurantJsonObject = Json.createObjectBuilder().add("restaurantId", restaurant.getRestaurantId())
				.add("name", restaurant.getName()).add("borough", restaurant.getBorough())
				.add("cuisine", restaurant.getCuisine()).add("isActive", restaurant.isActive())
				.add("categories", Json.createArrayBuilder(restaurant.getCategories())).add("address", addressBuilder)
				.add("grades", gradesBuilder).build();

		restaurantBuilder.add(restaurantJsonObject);

		// Build the Orders JSON array
		JsonArray restaurantArray = restaurantBuilder.build();

		try (JsonWriter writer = Json.createWriter(new FileWriter("data/VanChiHieu_21108211.json"))) {
			writer.writeArray(restaurantArray);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
