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

public class Exercise2bObjectToJSON {
	public static void main(String[] args) throws FileNotFoundException {
		Address address = new Address("21 2nd Street", "New York", "NY", 10021);

		List<Phone> phoneNumbers = Arrays.asList(new Phone("home", "212 555-1234"), new Phone("office", "646 555-4567"),
				new Phone("mobile", "123 456-7890"));
		List<Employee> employees = Arrays.asList(new Employee("John", "Smith", 30), new Employee("Mary", "Smith", 30),
				new Employee("John", "Doe", 30), new Employee("Mary", "Doe", 30));
		employees.forEach(employee -> {
			employee.setAddress(address);
			employee.setPhone(phoneNumbers);
		});
		writeJson(employees, "data/jsons3New.json");
	}

	private static void writeJson(List<Employee> employees, String string) {
		JsonObjectBuilder oBuilder = null;
		JsonArrayBuilder aBuilder = null;
		JsonWriter writer = null;
		try {
			oBuilder = Json.createObjectBuilder();
			aBuilder = Json.createArrayBuilder();
			writer = Json.createWriter(new FileWriter(string));
			for (Employee employee : employees) {
				Address address = employee.getAddress();
				List<Phone> phoneNumbers = employee.getPhone();
				JsonObject jsonAddress = oBuilder.add("streetAddress", address.getStreetAddress())
						.add("city", address.getCity()).add("state", address.getState())
						.add("postalCode", address.getPostalCode()).build();
				JsonArrayBuilder aBuilder2 = Json.createArrayBuilder();
				for (Phone phone : phoneNumbers) {
					JsonObject jsonPhone = oBuilder.add("type", phone.getType()).add("number", phone.getNumber())
							.build();
					aBuilder2.add(jsonPhone);
				}
				JsonArray jsonPhones = aBuilder2.build();
				JsonObject jsonEmployee = oBuilder.add("firstName", employee.getFirstName())
						.add("lastName", employee.getLastName()).add("age", employee.getAge())
						.add("address", jsonAddress).add("phoneNumbers", jsonPhones).build();
				aBuilder.add(jsonEmployee);
			}
			JsonArray jsonEmployees = aBuilder.build();
			writer.writeArray(jsonEmployees);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (writer != null) {
				writer.close();
			}
		}
	}

}
