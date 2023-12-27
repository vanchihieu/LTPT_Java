package demo;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

import com.google.gson.Gson;

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
		
		Gson gson = new Gson();
		
		String json = gson.toJson(cus);
		
		System.out.println(json);
	}

}
