package demo;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

public class TCPClient {

	public static void main(String[] args) throws Exception {

		BufferedReader userInput = new BufferedReader(
				new InputStreamReader(System.in));
		
		try (Socket soket = new Socket("192.168.175.193", 6789)) {
			
			DataOutputStream out = new DataOutputStream(
					soket.getOutputStream());

			BufferedReader in = new BufferedReader(
					new InputStreamReader(soket.getInputStream()));
			
			String line = "";
			while (!line.equals("Bye")) {
				line = userInput.readLine();
				
				out.writeBytes(line + "\n");
				out.flush();
				
				System.out.println("From Server: " + in.readLine());
			}
			userInput.close();
			in.close();
			out.close();
		}
	}
}
