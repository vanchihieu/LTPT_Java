package app;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Arrays;
import java.util.List;

import entities.Address;
import entities.Employee;
import entities.Phone;
import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;
import jakarta.json.JsonWriter;

public class Exercise2aObjectToJSON {
	public static void main(String[] args) throws FileNotFoundException {
		Employee employee = new Employee("John", "Smith", 30);
		employee.setAddress(new Address("21 2nd Street", "New York", "NY", 10021));

		List<Phone> phoneNumbers = Arrays.asList(new Phone("home", "212 555-1234"), new Phone("office", "646 555-4567"),
				new Phone("mobile", "123 456-7890"));
		employee.setPhone(phoneNumbers);
		writeJson(employee, "data/json3New.json");

	}

	private static void writeJson(Employee employee, String fileName) {
		JsonObjectBuilder oBuilder = null;
		JsonArrayBuilder aBuilder = null;
		JsonWriter writer = null;
		Address address = employee.getAddress();
		List<Phone> phoneNumbers = employee.getPhone();

		try {
			oBuilder = Json.createObjectBuilder();
			aBuilder = Json.createArrayBuilder();
			writer = Json.createWriter(new FileWriter(fileName));
			JsonObject jsonAddress = oBuilder.add("streetAddress", address.getStreetAddress())
					.add("city", address.getCity()).add("state", address.getState())
					.add("postalCode", address.getPostalCode()).build();

			for (Phone phone : phoneNumbers) {
				JsonObject jsonPhone = oBuilder.add("type", phone.getType()).add("number", phone.getNumber()).build();
				aBuilder.add(jsonPhone);
			}

			JsonArray jsonPhones = aBuilder.build();
			JsonObject jsonEmployee = oBuilder.add("firstName", employee.getFirstName())
					.add("lastName", employee.getLastName()).add("age", employee.getAge()).add("address", jsonAddress)
					.add("phoneNumbers", jsonPhones).build();

			writer.writeObject(jsonEmployee);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (writer != null)
				writer.close();
		}
	}

}
