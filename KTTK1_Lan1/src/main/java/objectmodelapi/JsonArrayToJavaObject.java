package objectmodelapi;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import entities.Address;
import entities.Grade;
import entities.Restaurant;
import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonNumber;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import jakarta.json.JsonValue;

public class JsonArrayToJavaObject {
	public static void main(String[] args) {
		try(JsonReader reader = Json.createReader(new FileReader("data/restaurants.json")) ) {
			JsonArray jsonArray = reader.readArray();
			List<Restaurant> restaurants = parseRestaurants(jsonArray);
			for (Restaurant restaurant : restaurants) {
				System.out.println(restaurant);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static List<Restaurant> parseRestaurants(JsonArray jsonArray) {
        List<Restaurant> restaurants = new ArrayList<>();
		
		for (JsonObject jo : jsonArray.getValuesAs(JsonObject.class)) {
			int restaurantId = jo.getInt("restaurantId");
			String name = jo.getString("name");
			String borough = jo.getString("borough");
            String cuisine = jo.getString("cuisine");
            boolean isActive = jo.getBoolean("isActive");
            
            // categories
            JsonArray categoriesArray = jo.getJsonArray("categories");
            List<String> categories = new ArrayList<>();
            for (JsonValue value : categoriesArray.getValuesAs(JsonValue.class)) {
            	String category = value.toString();
                categories.add(category);
            }
            
            // address
            JsonObject addressObject = jo.getJsonObject("address");
            String building = addressObject.getString("building");
            JsonArray coordArray = addressObject.getJsonArray("coord");
            List<Double> coord = new ArrayList<>();
            for (JsonNumber number : coordArray.getValuesAs(JsonNumber.class)) {
                coord.add(number.doubleValue());
            }
            String street = addressObject.getString("street");
            String zipcode = addressObject.getString("zipcode");
            
            // grades
            JsonArray gradesArray = jo.getJsonArray("grades");
            List<Grade> grades = new ArrayList<Grade>();
            for (JsonObject gradeObject : gradesArray.getValuesAs(JsonObject.class)) {
            	long date = gradeObject.getJsonNumber("date").longValue();
            	String grade = gradeObject.getString("grade");
            	int score = gradeObject.getInt("score");
            	Grade gradeObj = new Grade(date, grade, score);
            	grades.add(gradeObj);
			}
            
            Address address = new Address(building, coord, street, zipcode);
            Restaurant restaurant = new Restaurant(restaurantId, name, borough, cuisine, isActive, categories, address, grades);
            restaurants.add(restaurant);
		}
		return restaurants;
	}
}
