package app;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import entity.Employee;
import jakarta.json.Json;
import jakarta.json.JsonNumber;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

public class DecodeJsonReadFile {
	public static void main(String[] args) throws FileNotFoundException {
		InputStream in = new FileInputStream("json/employee.json");
		
		JsonReader reader = Json.createReader(in);
		JsonObject jo = reader.readObject();
		JsonNumber id = jo.getJsonNumber("id");
		String name = jo.getString("name");
		JsonNumber sal = jo.getJsonNumber("salary");
		
		Employee employee = new Employee(id.longValue(), name, sal.doubleValue());
		
		System.out.println(employee);
	}
}
