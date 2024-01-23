package app;

import util.PersonUtil;

public class ReadJsonTask {
	public static void main(String[] args) {
		String json = PersonUtil.readFromFile("data/person.json");
		System.out.println(json);
	}
}
