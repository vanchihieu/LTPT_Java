package demo;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {

	public static void main(String[] args) throws Exception {
		try (ServerSocket serverSocket = new ServerSocket(6789)) {
			
			System.out.println("Server ready");
			
			while (true) {
				Socket clientSocket = serverSocket.accept();
				
				// Read
				BufferedReader in = new BufferedReader(
						new InputStreamReader(clientSocket.getInputStream()));
				
				// Write => send to client
				DataOutputStream out = new DataOutputStream(
						clientSocket.getOutputStream());

				String line = "";
				while (!line.equals("Bye")) {
					line = in.readLine() + "\n";
					System.out.println("From client: " + line);
					
					// Write => server send to client
					out.writeBytes(line.toUpperCase());
					out.flush();
				}
			
				in.close();
				out.close();
			}	
		}
	}
}
