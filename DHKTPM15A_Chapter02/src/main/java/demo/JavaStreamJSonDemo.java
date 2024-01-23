package demo;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.json.Json;
import javax.json.stream.JsonGenerator;

import entity.Address;
import entity.Customer;
import entity.PhoneNumber;

public class JavaStreamJSonDemo {
	public static void main(String[] args) throws IOException {
		Customer cus = new Customer("Anna", "Nguyen", 20);
		cus.setAddress(new Address("12 Le Lai", "HCM CIty", "", 100000));
		List<PhoneNumber> phoneNumbers = Arrays.asList(new PhoneNumber("Mobi", "090512312"),
				new PhoneNumber("Fax", "0234242424"));
		cus.setPhoneNumbers(phoneNumbers);

		System.out.println(cus);

		toJson(cus, "data/customer2.json");

	}

	private static void toJson(Customer cus, String fileName) throws IOException {
		String json = "";

		JsonGenerator generator = Json.createGenerator(new FileWriter(fileName));
		generator.writeStartObject().write("firstName", cus.getFirstName()).write("lastName", cus.getLastName())
				.write("age", cus.getAge()).writeStartObject("address")
				.write("streetAddress", cus.getAddress().getStreetAddress()).writeEnd().writeEnd();

		generator.close();
	}
}
