package app;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import entities.Address;
import entities.Employee;
import entities.Phone;
import jakarta.json.Json;
import jakarta.json.stream.JsonParser;
import jakarta.json.stream.JsonParser.Event;

public class Exercise2bJSONToObjectAPI {
	public static void main(String[] args) throws IOException {
		List<Employee> employee = readFromFile("data/jsons3New.json");
		employee.forEach(em -> System.out.println(em));
	}

	private static List<Employee> readFromFile(String string) throws FileNotFoundException, IOException {
		List<Employee> employees = new ArrayList<Employee>();
		try (InputStream is = new FileInputStream(string); JsonParser parser = Json.createParser(is)) {
			InputStream fis = new FileInputStream(string);
			JsonParser jsonParser = Json.createParser(fis);
			Employee employee = null;
			Address address = null;
			List<Phone> phoneNumbers = null;
			String key = null;
			while (parser.hasNext()) {
				Event event = parser.next();
				switch (event) {
				case START_ARRAY:
					employees = new ArrayList<Employee>();
					break;
				case START_OBJECT:
					phoneNumbers = new ArrayList<Phone>();
					employee = new Employee();
					break;
				case KEY_NAME:
					key = parser.getString();
					break;
				case VALUE_STRING:
					setStringValues(employee, address, phoneNumbers, key, jsonParser.getString());
					break;
				case VALUE_NUMBER:
					setNumberValues(employee, address, key, jsonParser.getLong());
					break;
				case END_OBJECT:
					break;
				case END_ARRAY:
					employee.setAddress(address);
					employee.setPhone(phoneNumbers);
					break;
				default:
					break;
				}
			}
		}
		return employees;
	}

	private static void setNumberValues(Employee emp, Address address, String keyName, long value) {
		switch (keyName) {
		case "postalCode":
			address.setPostalCode((int) value);
			break;
		case "age":
			emp.setAge((int) value);
			break;
		default:
			break;
		}
	}

	private static void setStringValues(Employee emp, Address address, List<Phone> phones, String key, String value) {
		Phone phone = new Phone();

		switch (key) {
		case "firstName":
			emp.setFirstName(value);
			break;
		case "lastName":
			emp.setLastName(value);
			break;
		case "streetAddress":
			address.setStreetAddress(value);
			break;
		case "city":
			address.setCity(value);
			break;
		case "state":
			address.setState(value);
			break;

		case "type":
			phone.setType(value);
			phones.add(phone);
			break;
		case "number":
			phones.get(phones.size() - 1).setNumber(value);
			break;
		default:
			break;

		}
	}

}
