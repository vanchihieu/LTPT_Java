package streamapi;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

import entities.Address;
import entities.Grade;
import entities.Restaurant;
import jakarta.json.Json;
import jakarta.json.stream.JsonGenerator;

public class JavaObectToJson {
	public static void main(String[] args) {
		// Tạo đối tượng restaurant
		Restaurant restaurant = new Restaurant();
		restaurant.setRestaurantId(30000001);
		restaurant.setName("Morris Park Bake Shop");
		restaurant.setBorough("Bronx");
		restaurant.setCuisine("Bakery");
		restaurant.setActive(false);
		restaurant.setCategories(Arrays.asList("Seafood", "Fastfood"));
		restaurant.setAddress(new Address("1007", Arrays.asList(-73.856077, 40.848447), "Morris Park Ave", "10462"));
		restaurant.setGrades(Arrays.asList(new Grade(1393804800000L, "A", 2), new Grade(1378857600000L, "A", 6),
				new Grade(1358985600000L, "A", 10)));

		try (FileWriter fileWriter = new FileWriter("data/VanChiHieu_21108211.json")) {
			// Tạo JsonGenerator để tạo tài liệu JSON và ghi vào file
			JsonGenerator jsonGenerator = Json.createGenerator(fileWriter);

			// Bắt đầu ghi tài liệu JSON
			jsonGenerator.writeStartArray().writeStartObject().write("restaurantId", restaurant.getRestaurantId())
					.write("name", restaurant.getName()).write("borough", restaurant.getBorough())
					.write("cuisine", restaurant.getCuisine()).write("isActive", restaurant.isActive())
					.writeStartArray("categories");
			for (String category : restaurant.getCategories()) {
				jsonGenerator.write(category);
			}
			jsonGenerator.writeEnd().writeStartObject("address")
					.write("building", restaurant.getAddress().getBuilding()).writeStartArray("coord");
			for (Double coord : restaurant.getAddress().getCoord()) {
				jsonGenerator.write(coord);
			}
			jsonGenerator.writeEnd().write("street", restaurant.getAddress().getStreet())
					.write("zipcode", restaurant.getAddress().getZipcode()).writeEnd().writeStartArray("grades");
			for (Grade grade : restaurant.getGrades()) {
				jsonGenerator.writeStartObject().write("date", grade.getDate()).write("grade", grade.getGrade())
						.write("score", grade.getScore()).writeEnd();
			}
			jsonGenerator.writeEnd().writeEnd().writeEnd();

			// Đóng JsonGenerator
			jsonGenerator.close();

			System.out.println("Tai lieu json da duoc ghi");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}