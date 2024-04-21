package demo;

import java.net.MalformedURLException;
import java.net.URL;

public class TestParsingURL {

	public static void main(String[] args) {
		try {
			URL url = new URL("https://azure.microsoft.com/en-us/pricing/#product-pricing");
			System.out.println("The protocol is: " + url.getProtocol());
			System.out.println("The host is: " + url.getHost());
			System.out.println("The port is: " + url.getPort());
			System.out.println("The file is: " + url.getFile());
			System.out.println("The anchor is: " + url.getRef());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}

}
