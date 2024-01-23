package util;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import entity.Address;
import entity.Person;
import entity.Phone;
import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import jakarta.json.JsonValue;
import jakarta.json.stream.JsonParser;
import jakarta.json.stream.JsonParser.Event;

public class PersonUtil {
	public static String readFromFile(String path) {
		FileReader reader = null;
		JsonReader jsonReader = null;

		try {
			reader = new FileReader(path);
			jsonReader = Json.createReader(reader);

			return jsonReader.readObject().toString();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				reader.close();
				jsonReader.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return null;
	}

	// streaming api
	public static Person fromJson(String json) {
		Person p = null;
		String keyName = "";
		Address address = null;
		List<Phone> phones = null;
		
		StringReader reader = new StringReader(json);

		JsonParser parser = Json.createParser(reader);

		while (parser.hasNext()) {
			Event event = parser.next();
			switch (event) {
			case KEY_NAME:
				keyName = parser.getString();
//				System.out.println(keyName);
				break;

			case START_OBJECT:
				if (keyName.equals("address"))
					address = new Address();
				else
					p = new Person();

				break;
				
			case VALUE_NUMBER:
				if(parser.isIntegralNumber()) {
					if(keyName.equals("age"))
						p.setAge(parser.getInt());
					else
						address.setPostalCode(parser.getInt());
				}
				break;
			
			case VALUE_STRING:
				String sValue = parser.getString();
				setStringValue(p, address, keyName, sValue);
				break;
			
			case START_ARRAY:
				phones = new ArrayList<Phone>();
				JsonArray ja = parser.getArray();
				for (JsonValue jsonValue : ja) {
					JsonObject jo = jsonValue.asJsonObject();
					Phone ph = new Phone(jo.getString("type"), jo.getString("number"));
					phones.add(ph);
				}
				break;
			default:
				break;
			}
		}
		
		p.setPhoneNumbers(phones);
		p.setAddress(address);
		return p;
	}

	private static void setStringValue(Person p, Address address, String keyName, String sValue) {
		switch (keyName) {
		case "firstName":
			p.setFirstName(sValue);
			break;
		case "lastName":
			p.setLastName(sValue);
			break;
		case "city":
			address.setCity(sValue);
			break;
		case "streetAddress":
			address.setStreetAddress(sValue);
			break;
		case "state":
			address.setState(sValue);
			break;
		
		default:
			break;
		}
	}
}
