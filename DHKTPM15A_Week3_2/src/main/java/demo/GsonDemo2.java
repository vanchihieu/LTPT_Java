package demo;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

import com.google.gson.Gson;

import entity.Customer;

public class GsonDemo2 {
	public static void main(String[] args) throws FileNotFoundException {
		Gson gson = new Gson();
		
		Scanner scanner = new Scanner(new FileReader("data/customer2.json"));
		
		String json = "";
		while(scanner.hasNextLine()) {
			String line = scanner.nextLine();
			
			json += line;
			
		}
		
		Customer customer = gson.fromJson(json, Customer.class);
		
		System.out.println(customer);
	}
}
