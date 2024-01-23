package util;

import java.io.FileReader;
import java.io.IOException;

import entity.State;
import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.stream.JsonParser;
import jakarta.json.stream.JsonParser.Event;

public class StateUtil {
	private static final String PATH = "data/states.json";

	// streaming api
	public static State findByAb(String abb) {

		State state = null;
		FileReader reader = null;
		JsonParser parser = null;

		try {
			reader = new FileReader(PATH);

			parser = Json.createParser(reader);

			while (parser.hasNext()) {
				Event event = parser.next();

				if (event.equals(Event.START_OBJECT)) {
					JsonObject jo = parser.getObject();
					System.out.println(jo);
					if (jo.getString("Abbreviation").equalsIgnoreCase(abb)) {
						state = new State(jo.getString("StateName"), jo.getString("Abbreviation"),
								jo.getString("Capital"), jo.getInt("Statehood"), jo.getInt("ID"));
						return state;
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return state;
	}
}
