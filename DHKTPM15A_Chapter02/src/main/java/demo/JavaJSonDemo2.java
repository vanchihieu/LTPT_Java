package demo;

import java.io.StringReader;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;

import entity.PhoneNumber;

public class JavaJSonDemo2 {
	public static void main(String[] args) {
		String json = "{\"type\":\"Mobi\",\"number\":\"090512312\"}";
		
		JsonReader reader = Json.createReader(new StringReader(json));
		
		JsonObject jo = reader.readObject();
		
		PhoneNumber ph = new PhoneNumber(jo.getString("type"), jo.getString("number"));
		
		System.out.println(ph);
	}
}
