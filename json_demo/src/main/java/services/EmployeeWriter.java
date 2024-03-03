package services;

import java.io.StringWriter;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import entities.Employee;
import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;
import jakarta.json.JsonWriter;
import jakarta.json.stream.JsonGenerator;

public class EmployeeWriter {

	private Employee employee;
	
	public EmployeeWriter(Employee employee) {
		this.employee = employee;
	}
	
	public JsonObject convertToJsonObject() {
		JsonObjectBuilder jsonBuilder = Json.createObjectBuilder();
		
		JsonObject jo = jsonBuilder
				.add("id", employee.getId())
				.add("name", employee.getName())
				.add("salary", employee.getSalary())
				.build();
		
		return jo;
	}
	public String write() {
		JsonObjectBuilder jsonBuilder = Json.createObjectBuilder();
		
		JsonObject jo = jsonBuilder
				.add("id", employee.getId())
				.add("name", employee.getName())
				.add("salary", employee.getSalary())
				.build();
		
		System.out.println(jo.toString());
		
		// Get the JSON string from the JsonObject
		// Create a StringWriter to store the formated JSON
		StringWriter stringWriter = new StringWriter();
		
		// Create a JsonWriter with pretty printing (indentation) configuration
		Map<String, Boolean> config = new HashMap<>();
		config.put(JsonGenerator.PRETTY_PRINTING, true);
		
		JsonWriter jsonWriter = Json.createWriterFactory(config)
				.createWriter(stringWriter);
		
		jsonWriter.write(jo);
		jsonWriter.close();
				
		System.out.println(stringWriter);
		
		return stringWriter.toString();
	}
}
