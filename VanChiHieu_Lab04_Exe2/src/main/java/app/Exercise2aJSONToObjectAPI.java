package app;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import entities.Address;
import entities.Employee;
import entities.Phone;
import jakarta.json.Json;
import jakarta.json.stream.JsonParser;
import jakarta.json.stream.JsonParser.Event;

public class Exercise2aJSONToObjectAPI {
	public static void main(String[] args) throws FileNotFoundException {
		// read file json json3NewApi.json use json-p streaming api
		// Employee employee = readFromFile("data/json3.json");
		Employee employee = new Employee();
		Address address = new Address();
		List<Phone> phoneNumbers = new ArrayList<Phone>();
		InputStream fis = new FileInputStream("data/json3.json");
		JsonParser jsonParser = Json.createParser(fis);
		// JsonReaderFactory factory = Json.createReaderFactory(null);
		// jsonParser = (JsonParser) factory.createReader(fis);
		String keyName = null;
		while (jsonParser.hasNext()) {
			Event event = jsonParser.next();
			switch (event) {
			case KEY_NAME:
				keyName = jsonParser.getString();
				break;
			case VALUE_STRING:
				setStringValues(employee, address, phoneNumbers, keyName, jsonParser.getString());
				break;
			case VALUE_NUMBER:
				setNumberValues(employee, address, keyName, jsonParser.getLong());
				break;
			default:
				break;
			}
		}
		employee.setAddress(address);
		employee.setPhone(phoneNumbers);
		System.out.println(employee);
		jsonParser.close();
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
