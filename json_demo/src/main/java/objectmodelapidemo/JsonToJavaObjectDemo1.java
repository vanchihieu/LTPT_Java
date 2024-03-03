package objectmodelapidemo;

import java.io.StringReader;

import entities.Employee;
import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import services.EmployeeBuilder;

public class JsonToJavaObjectDemo1 {

	public static void main(String[] args) {
		String json = "{\"id\":1,\"name\":\"John Nguyen\",\"salary\":1000.0}";
		
		
//		JsonReader reader = Json.createReader(new StringReader(json));
//		JsonObject jo = reader.readObject();
//		
//		Employee employee = new Employee(
//				jo.getJsonNumber("id").longValue(),
//				jo.getString("name"),
//				jo.getJsonNumber("salary").doubleValue()
//				);
		
		EmployeeBuilder empBuilder = new EmployeeBuilder(json);
		
		System.out.println(empBuilder.build());
	}

}