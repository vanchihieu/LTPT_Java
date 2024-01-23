package demo;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonValue;

import entity.PhoneNumber;

public class JavaJsonDemo4 {
	public static void main(String[] args) {
		List<PhoneNumber> phoneNumbers = revertFromJson("data/customer.json");
		System.out.println(phoneNumbers);
	}
	
	// đọc file, chuyển lại thành lớp đối tượng
	private static List<PhoneNumber> revertFromJson(String fileName) {
		List<PhoneNumber> phoneNumbers = new ArrayList<PhoneNumber>();
		JsonReader reader = null;
		
		try {
			reader = Json.createReader(new FileReader(fileName));
			
			JsonArray ja = reader.readArray();
			for(JsonValue v : ja) {
				if(v instanceof JsonObject) {
					JsonObject jo = v.asJsonObject();
					PhoneNumber ph = new PhoneNumber(jo.getString("type"), jo.getString("number"));
					phoneNumbers.add(ph);
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			reader.close();
		}

		return phoneNumbers;
	}
}
