package jacksondemo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import entities.Employee;

public class JacksonJsonToJavaObjectMapperDemo {

	private static Employee employee;
	public static void main(String[] args) {
		String employeeJson ="{\"id\":1,\"name\":\"John Smith\",\"salary\":1000.0}";
		ObjectMapper objectMapper = new ObjectMapper();
  
        try {
			employee = objectMapper.readValue(employeeJson, Employee.class);
			System.out.println(employee.toString());
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
