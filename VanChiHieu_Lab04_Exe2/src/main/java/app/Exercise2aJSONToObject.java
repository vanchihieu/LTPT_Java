package app;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import entities.Address;
import entities.Employee;
import entities.Phone;
import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import jakarta.json.JsonValue;

public class Exercise2aJSONToObject {
	public static void main(String[] args) throws FileNotFoundException {

		Employee employee = readFromFile("data/json3.json");
		System.out.println(employee);
	}

	private static Employee readFromFile(String fileName) {
		Employee employee = new Employee();

		JsonReader reader = null;
		try {
			reader = Json.createReader(new FileReader(fileName));
			JsonObject jsonObjectEmployee = reader.readObject();

			employee.setFirstName(jsonObjectEmployee.getString("firstName"));
			employee.setLastName(jsonObjectEmployee.getString("lastName"));
			employee.setAge(jsonObjectEmployee.getInt("age"));

			JsonObject jsonObjectAddress = jsonObjectEmployee.getJsonObject("address");

			Address jsonAddress = new Address(jsonObjectAddress.getString("streetAddress"),
					jsonObjectAddress.getString("city"), jsonObjectAddress.getString("state"),
					jsonObjectAddress.getInt("postalCode"));

			employee.setAddress(jsonAddress);
			JsonArray jsonArrayPhone = jsonObjectEmployee.getJsonArray("phoneNumbers");
			List<Phone> phoneNumbers = new ArrayList<Phone>();
			for (JsonValue v : jsonArrayPhone) {
				if (v instanceof JsonObject) {
					JsonObject jsonObjectPhone = (JsonObject) v;
					phoneNumbers.add(new Phone(jsonObjectPhone.getString("type"), jsonObjectPhone.getString("number")));
				}
			}
			employee.setPhone(phoneNumbers);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				reader.close();
			}
		}
		return employee;
	}
}
