package objectmodelapidemo;
import java.io.StringWriter;
import java.util.Collections;

import entities.Employee;
import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;
import jakarta.json.JsonWriter;
import jakarta.json.stream.JsonGenerator;

public class JavaObjectToJsonDemo1 {

	public static void main(String[] args) {
		Employee employee = new Employee(1L, "John Nguyen", 1000d);
		
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
		JsonWriter jsonWriter = Json
				.createWriterFactory(
						Collections.singletonMap(
								JsonGenerator.PRETTY_PRINTING, true))
				.createWriter(stringWriter);
		jsonWriter.write(jo);
		jsonWriter.close();
				
		System.out.println(stringWriter);
	}

}
