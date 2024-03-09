package app;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Arrays;
import java.util.List;

import entities.Address;
import entities.Employee;
import entities.Phone;
import jakarta.json.Json;
import jakarta.json.stream.JsonGenerator;

public class Exercise2aObjectToJSONAPI {
	public static void main(String[] args) throws FileNotFoundException {
		Employee employee = new Employee("John", "Smith", 30);
		employee.setAddress(new Address("21 2nd Street", "New York", "NY", 10021));

		List<Phone> phoneNumbers = Arrays.asList(new Phone("home", "212 555-1234"), new Phone("office", "646 555-4567"),
				new Phone("mobile", "123 456-7890"));
		employee.setPhone(phoneNumbers);
		writeJson(employee, "data/json3NewApi.json");

	}

	private static void writeJson(Employee employee, String fileName) {
		Address address = employee.getAddress();
		List<Phone> phoneNumbers = employee.getPhone();
		try {
			JsonGenerator generator = Json.createGenerator(new FileWriter(fileName));
			generator.writeStartObject().write("firstName", employee.getFirstName())
					.write("lastName", employee.getLastName()).write("age", employee.getAge())
					.writeStartObject("address").write("streetAddress", address.getStreetAddress())
					.write("city", address.getCity()).write("state", address.getState())
					.write("postalCode", address.getPostalCode()).writeEnd().writeKey("phoneNumbers").writeStartArray();
			phoneNumbers.forEach(phone -> {
				generator.writeStartObject().write("type", phone.getType()).write("number", phone.getNumber())
						.writeEnd();
			});
			generator.writeEnd().writeEnd();
			generator.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
