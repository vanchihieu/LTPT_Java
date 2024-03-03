package app;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

import entity.Employee;
import jakarta.json.Json;
import jakarta.json.JsonObject;

public class EncodeJsonWriteFile {
	public static void main(String[] args) throws Exception {
		EncodeJsonWriteFile ej = new EncodeJsonWriteFile();
		
		Employee emp = new Employee(1001, "John Smith", 10000d);
		
		String js = ej.generateToJson(emp);
		System.out.println("Employee:");
		System.out.println(js);
		ej.export("json/employee.json", js);
	}
	
	private String generateToJson(Employee p) {
//		JsonObjectBuilder jsonBuilder = Json.createObjectBuilder();
//		JsonObject jsonObject = Json.createObjectBuilder()
//				.add("id", p.getId())
//				.add("name", p.getName())
//				.add("salary", p.getSalary())
//				.build();
//		return jsonObject.toString();

		JsonObject jsonObject = Json.createObjectBuilder()
				.add("id", p.getId())
				.add("name", p.getName())
				.add("salary", p.getSalary())
				.build();
		return jsonObject.toString();
		
	}
	
	private void export(String filePath, String json) throws Exception {
		PrintWriter out = new PrintWriter(new FileOutputStream(filePath), true);
		out.print(json);
		out.close();
	}
}
