package demo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class URLConnectionReaderTest {

	public static void main(String[] args) throws IOException {
		URL url = new URL("https://www.google.com/");
	    URLConnection urlConn = url.openConnection();
	    
	    urlConn.connect();
	    
	    BufferedReader in = new BufferedReader(
	    		new InputStreamReader(urlConn.getInputStream()));
	    String inputLine;

	    while ((inputLine = in.readLine()) != null) {
	      System.out.println(inputLine);
	    }
	    in.close();
	}
}
