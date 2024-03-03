package objectmodelapidemo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import entities.Employee;
import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonNumber;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

public class JsonArrayToJavaObjectDemo2 {

	public static void main(String[] args) throws FileNotFoundException {
		String json = "[{\"id\":10001,\"name\":\"John Smith\",\"salary\":10000.0},"
				+ "{\"id\":10002,\"name\":\"Tom Cruise\",\"salary\":20000.0}]";
		
		JsonReader reader = Json.createReader(new StringReader(json));
		JsonArray ja = reader.readArray();
		
		List<Employee> employees = new ArrayList<Employee>();
		
		for(JsonObject jo:ja.getValuesAs(JsonObject.class)) {
			JsonNumber id = jo.getJsonNumber("id"); 
			String name = jo.getString("name"); 
			JsonNumber sal = jo.getJsonNumber("salary"); 
			employees.add(new Employee(id.longValue(), name, sal.doubleValue()));
		}
		
		System.out.println(employees);
	}


}
