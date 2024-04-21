package demo;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TCPServerMultiClient {

	public static void main(String[] args) throws Exception {

		try (ServerSocket serverSocket = new ServerSocket(6789)) {
			// Create a thread pool to handle multiple clients
			ExecutorService executorService = Executors.newCachedThreadPool();

			// Start accepting client connections
			System.out.println("Server listening on port 6789...");

			while (true) {
				// Accept a client connection
				Socket clientSocket = serverSocket.accept();
				System.out.println("Client connected: " + clientSocket.getInetAddress().getHostAddress());

				// Create a new thread for the client and submit it to the thread pool
				ClientHandler clientHandler = new ClientHandler(clientSocket);
				executorService.execute(clientHandler);
			}
		}
	}
	private static class ClientHandler implements Runnable {
		private Socket clientSocket;
		public ClientHandler(Socket clientSocket) {
			this.clientSocket = clientSocket;
		}

		@Override
		public void run() {

		}
	}
}
