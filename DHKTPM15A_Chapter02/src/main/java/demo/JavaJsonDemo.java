package demo;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

import entity.Address;
import entity.Customer;
import entity.PhoneNumber;

public class JavaJsonDemo {
	public static void main(String[] args) {
		Customer cus = new Customer("Anna", "Nguyen", 20);
		
		cus.setAddress(new Address("12 Le Lai", "HCM CIty", "", 100000));
		
		List<PhoneNumber> phoneNumbers = Arrays.asList(new PhoneNumber("Mobi", "090512312"),
				new PhoneNumber("Fax", "0234242424"));
		cus.setPhoneNumbers(phoneNumbers);

//		System.out.println(cus);
//
//		String json = convertJava2Json(cus);
//		System.out.println(json);
		
		write2File(cus, "data/customer.json");
	}

	private static String convertJava2Json(Customer cus) {
		JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
		JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();

		List<PhoneNumber> phoneNumbers = cus.getPhoneNumbers();

		for (PhoneNumber p : phoneNumbers) {
			JsonObject jp = objectBuilder.add("type", p.getType()).add("number", p.getNumber()).build();
			arrayBuilder.add(jp);
		}

		JsonArray ja = arrayBuilder.build();

		JsonObject jCus = objectBuilder.add("firstName", cus.getFirstName()).add("lastName", cus.getLastName())
				.add("age", cus.getAge()).add("phoneNumbers", ja).build();

//		System.out.println(ja.toString());
//		System.out.println(jObject);

		return jCus.toString();

	}

	private static void write2File(Customer cus, String fileName) {
		JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
		JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();

		List<PhoneNumber> phoneNumbers = cus.getPhoneNumbers();

		for (PhoneNumber p : phoneNumbers) {
			JsonObject jp = objectBuilder.add("type", p.getType()).add("number", p.getNumber()).build();
			arrayBuilder.add(jp);
		}

		JsonArray ja = arrayBuilder.build(); // json array -> phone numbers

		PrintWriter out = null;
		try {
			out = new PrintWriter(new FileWriter(fileName), true);
			out.println(ja.toString());
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(out != null)
				out.close();
		}

	}
}
