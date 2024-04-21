package demo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class URLConnectionSendRequestTest {

	public static void main(String[] args) throws IOException {
		URL url = new URL("https://timkiem.vnexpress.net/");
		sendRequest(url, "GET", "search_q=vang");
	}

	private static void sendRequest(URL url, String method, String params) throws IOException {
		HttpURLConnection httpConn = (HttpURLConnection) 
				url.openConnection();
		httpConn.setRequestMethod(method.toUpperCase());
		httpConn.setDoOutput(true);
		
		OutputStreamWriter ow = new OutputStreamWriter(
				httpConn.getOutputStream(), "UTF-8");
		ow.write(params); // send data to server
		ow.flush();
		ow.close();
				
		int responseCode = httpConn.getResponseCode();
		System.out.println("GET Response Code :: " + responseCode);
		
		if (responseCode == HttpURLConnection.HTTP_OK) { // success
			BufferedReader in = new BufferedReader(
					new InputStreamReader(httpConn.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				System.out.println(inputLine);
				response.append(inputLine);
			}
			in.close();

			// print result
//			System.out.println(response.toString());
		} else {
			System.out.println("GET request did not work.");
		}
	}
}
