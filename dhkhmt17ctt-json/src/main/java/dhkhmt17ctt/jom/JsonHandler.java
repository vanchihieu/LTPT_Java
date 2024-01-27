package dhkhmt17ctt.jom;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import dhkhmt17ctt.entity.Address;
import dhkhmt17ctt.entity.Person;
import dhkhmt17ctt.entity.PhoneNumber;
import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;
import jakarta.json.JsonReader;
import jakarta.json.JsonValue;
import jakarta.json.JsonWriter;

public class JsonHandler {
	
	
	public static String toJson(Person p) {
		StringWriter st;
		
		try(JsonWriter writer = Json.createWriter(st = new StringWriter())){
			JsonObjectBuilder oBuilder = Json.createObjectBuilder();
			
			Address add = p.getAddress();
			
			JsonObject joAdd = oBuilder
			.add("streetAddress", add.getStreetAddress())
			.add("city", add.getCity())
			.build();
			
			
			JsonObject jo = oBuilder.add("firstName", p.getFirstName())
			.add("age", p.getAge())
			.add("address", joAdd)
			
			.build();
			
			writer.writeObject(jo);
		}
		
		return st.toString();
	}
	
	public static String toJson(List<Person> p) {
	
		return "";
	}
	
	
	
	public static List<Person> getPersons(String fileName) {
		
		List<Person> list = null;
		
		try(JsonReader reader = Json.createReader(new FileReader(fileName))){
			
			JsonArray ja = reader.readArray();
			if(ja != null)
				list = new ArrayList<>();
			
			for(JsonValue jv : ja) {
				
				Person p = new Person();
				
				if(jv instanceof JsonObject) {
					JsonObject jo = (JsonObject)jv;
					p.setFirstName(jo.getString("firstName"));
					p.setAge(jo.getInt("age"));
//					
					list.add(p);
				}
			}
			
		}catch (IOException e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	
	public static void toJsonFile(List<Person> persons, String fileName) {
		
		try(JsonWriter writer = Json.createWriter(new FileWriter(fileName))){
			
			JsonObjectBuilder oBuilder = Json.createObjectBuilder();
			JsonArrayBuilder aBuilder = Json.createArrayBuilder();
			
			persons.forEach(p -> {
				
				Address add = p.getAddress();
				JsonObject joAdd = null;
				if(add != null) {
					joAdd = oBuilder.add("city", add.getCity())
					.add("state", add.getState())
					.build();
				}
				oBuilder.add("firstName", p.getFirstName())
				.add("age", p.getAge());
				
				if(joAdd != null)
					oBuilder.add("address",joAdd);
				
				JsonObject jo = oBuilder.build();
				
				aBuilder.add(jo);
			});
			
			JsonArray ja = aBuilder.build();
			
			writer.write(ja);
		}catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
	public static Person fromJson(String fileName) {
		Person p = null;
		try(JsonReader reader = Json.createReader(new FileReader(fileName))){
			JsonObject jo = reader.readObject();
			if(jo != null) {
				p = new Person();
				p.setFirstName(jo.getString("firstName"));
				p.setAge(jo.getInt("age"));
				
				JsonObject joAdd = jo.getJsonObject("address");
				Address address = new Address(
						joAdd.getString("streetAddress"),
						joAdd.getString("city"), 
						joAdd.getString("state"), 
						joAdd.getInt("postalCode"));
				
				p.setAddress(address);
				
				List<PhoneNumber> phones = new ArrayList<>(); 
				JsonArray ja = jo.getJsonArray("phoneNumbers");
				for(JsonValue jv : ja) {
					if(jv instanceof JsonObject) {
						JsonObject joPh = (JsonObject)jv;
						phones.add(new PhoneNumber(joPh.getString("type"),
								joPh.getString("number")));
					}
				}
				
				p.setPhoneNumbers(phones);
			}
			
		}catch (IOException e) {
			e.printStackTrace();
		}
		
		return p;
	}
	
}
//upcasting
//downcasting

//

