package streamapi;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import entities.Grade;
import entities.Restaurant;
import jakarta.json.Json;
import jakarta.json.stream.JsonParser;

public class JsonToJavaObject {
	public static void main(String[] args) {
		List<Restaurant> restaurants = new ArrayList<>();

		Restaurant currentRestaurant = null;
		String currentKey = null;
		try {
			FileInputStream fileInputStream = new FileInputStream("data/VanChiHieu_21108211.json");
			JsonParser jsonParser = Json.createParser(fileInputStream);

			while (jsonParser.hasNext()) {
				JsonParser.Event event = jsonParser.next();

				switch (event) {

				case START_OBJECT:
					if (currentRestaurant == null) {
						currentRestaurant = new Restaurant();
					}
					break;
				case END_OBJECT:
					restaurants.add(currentRestaurant);
					break;
				case START_ARRAY:
					if ("categories".equals(currentKey)) {
						currentRestaurant.setCategories(parseStringArray(jsonParser));
					} else if ("coord".equals(currentKey)) {
//						currentRestaurant.setCoord(parseDoubleArray(jsonParser));
						currentRestaurant.getAddress().setCoord(parseDoubleArray(jsonParser));

//						System.out.println(currentRestaurant.getAddress().getco);
					} else if ("grades".equals(currentKey)) {
						currentRestaurant.setGrades(parseGradesArray(jsonParser));
					}
					break;
				case KEY_NAME:
					currentKey = jsonParser.getString();
					break;
				case VALUE_STRING:
					if (currentRestaurant != null) {
						parseStringValue(currentKey, jsonParser.getString(), currentRestaurant);
					}
//					System.out.println(currentRestaurant);
					break;
				case VALUE_NUMBER:
					if (currentRestaurant != null) {
						parseNumberValue(currentKey, jsonParser.getLong(), currentRestaurant);
					}
					break;
				case VALUE_TRUE:
					if (currentRestaurant != null) {
						parseBooleanValue(currentKey, true, currentRestaurant);
					}
					break;
				case VALUE_FALSE:
					if (currentRestaurant != null) {
						parseBooleanValue(currentKey, false, currentRestaurant);
					}
					break;

				default:
					break;
				}
			}

			// In kết quả
			for (Restaurant restaurant : restaurants) {
				System.out.println(restaurant);
			}
			System.out.println(restaurants.size());

			jsonParser.close();
			fileInputStream.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void parseStringValue(String key, String value, Restaurant currentRestaurant) {
		switch (key) {
		case "name":
			currentRestaurant.setName(value);
			break;
		case "borough":
			currentRestaurant.setBorough(value);
			break;
		case "cuisine":
			currentRestaurant.setCuisine(value);
			break;
		case "isActive":
			currentRestaurant.setActive(Boolean.parseBoolean(value));
			break;
		case "building":
			currentRestaurant.getAddress().setBuilding(value);
			break;
		case "street":
			currentRestaurant.getAddress().setStreet(value);
			break;
		case "zipcode":
			currentRestaurant.getAddress().setZipcode(value);
			break;
		default:
			break;
		}
	}

	private static void parseNumberValue(String key, long value, Restaurant currentRestaurant) {
		switch (key) {
		case "restaurantId":
			currentRestaurant.setRestaurantId((int) value);
			break;
		case "date":
			currentRestaurant.getGrades().get(currentRestaurant.getGrades().size() - 1).setDate(value);
			break;
		case "score":
			currentRestaurant.getGrades().get(currentRestaurant.getGrades().size() - 1).setScore((int) value);
			break;
		default:
			break;
		}
	}

	private static void parseBooleanValue(String key, boolean value, Restaurant currentRestaurant) {
		if ("isActive".equals(key)) {
			currentRestaurant.setActive(value);
		}
	}

	private static List<String> parseStringArray(JsonParser jsonParser) {
		List<String> values = new ArrayList<>();
		while (jsonParser.hasNext()) {
			JsonParser.Event event = jsonParser.next();
			if (event == JsonParser.Event.VALUE_STRING) {
				values.add(jsonParser.getString());
			} else if (event == JsonParser.Event.END_ARRAY) {
				break;
			}
		}
		return values;
	}

	private static List<Double> parseDoubleArray(JsonParser jsonParser) {
		List<Double> values = new ArrayList<>();
		while (jsonParser.hasNext()) {
			JsonParser.Event event = jsonParser.next();
			if (event == JsonParser.Event.VALUE_NUMBER) {
				values.add(jsonParser.getBigDecimal().doubleValue());
			} else if (event == JsonParser.Event.END_ARRAY) {
				break;
			}
		}
		return values;
	}

	private static List<Grade> parseGradesArray(JsonParser jsonParser) {
		List<Grade> grades = new ArrayList<>();
		Grade currentGrade = null;
		String currentKey = null;

		while (jsonParser.hasNext()) {
			JsonParser.Event event = jsonParser.next();

			switch (event) {
			case START_OBJECT:
				currentGrade = new Grade();
				break;
			case END_OBJECT:
				grades.add(currentGrade);
				break;
			case KEY_NAME:
				currentKey = jsonParser.getString();
				break;
			case VALUE_STRING:
				parseGradeStringValue(currentKey, jsonParser.getString(), currentGrade);
				break;
			case VALUE_NUMBER:
				parseGradeNumberValue(currentKey, jsonParser.getLong(), currentGrade);
				break;
			default:
				break;
			}
		}

		return grades;
	}

	private static void parseGradeStringValue(String key, String value, Grade currentGrade) {
		switch (key) {
		case "grade":
			currentGrade.setGrade(value);
			break;
		default:
			break;
		}
	}

	private static void parseGradeNumberValue(String key, long value, Grade currentGrade) {
		switch (key) {
		case "date":
			currentGrade.setDate(value);
			break;
		case "score":
			currentGrade.setScore((int) value);
			break;
		default:
			break;
		}
	}
}