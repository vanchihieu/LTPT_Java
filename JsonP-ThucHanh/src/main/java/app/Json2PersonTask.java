package app;

import entity.Person;
import util.PersonUtil;

public class Json2PersonTask {
	public static void main(String[] args) {
		String json = "{\"firstName\":\"John\",\"lastName\":\"Smith\",\"age\":25,\"address\":{\"streetAddress\":\"21 2nd Street\",\"city\":\"New York\",\"state\":\"NY\",\"postalCode\":10021},\"phoneNumbers\":[{\"type\":\"home\",\"number\":\"212 555-1234\"},{\"type\":\"fax\",\"number\":\"646 555-4567\"}]}\r\n"
				+ "";
		Person p = PersonUtil.fromJson(json);
		
		System.out.println(p);
	}
}
