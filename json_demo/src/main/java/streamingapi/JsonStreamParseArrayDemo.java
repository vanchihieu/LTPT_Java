package streamingapi;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import jakarta.json.Json;
import jakarta.json.stream.JsonParser;
import jakarta.json.stream.JsonParser.Event;
import jakarta.json.stream.JsonParserFactory;

public class JsonStreamParseArrayDemo {

	public static void main(String[] args) throws FileNotFoundException {
		InputStream is = new FileInputStream("json/posts.json");

        JsonParserFactory factory = Json.createParserFactory(null);
        JsonParser parser = factory.createParser(is, StandardCharsets.UTF_8);

        if (!parser.hasNext() && parser.next() != JsonParser.Event.START_ARRAY) {
            return;
        }

        // looping over object attributes
        while (parser.hasNext()) {
            Event event = parser.next();
            // starting object
            if (event == JsonParser.Event.START_OBJECT) {
                while (parser.hasNext()) {
                    event = parser.next();
                    if (event == JsonParser.Event.KEY_NAME) {
                        String keyName = parser.getString();
                        switch (keyName) {
                            case "id":
                                parser.next();
                                System.out.printf("id: %s%n", parser.getString());
                                break;
                            case "title":
                                parser.next();
                                System.out.printf("title: %s%n", parser.getString());
                                break;
                            case "description":
                                parser.next();
                                System.out.printf("description: %s%n%n", parser.getString());
                                break;
                            case "content":
                                parser.next();
                                System.out.printf("content: %s%n%n", parser.getString());
                                break;
                        }
                    }
                }
            }
        }
	}

}

