package services;

import java.io.StringReader;

import entities.Employee;
import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

public class EmployeeBuilder {
	
	private String jsonSring;
	
	public EmployeeBuilder(String jsonString) {
		this.jsonSring = jsonString;
	}
	
	public Employee build() {
		JsonReader reader = Json.createReader(new StringReader(this.jsonSring));
		JsonObject jo = reader.readObject();
		
		Employee employee = new Employee(
				jo.getJsonNumber("id").longValue(),
				jo.getString("name"),
				jo.getJsonNumber("salary").doubleValue()
				);
		
		return employee;
	}
}
