package demo;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPServer {

	public static void main(String[] args) throws Exception {
		try (DatagramSocket serverSocket = new DatagramSocket(9876)) {
			while (true) {
				
				// Server receive data from Client
				byte[] receiveData = new byte[1024];
				
				DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
				serverSocket.receive(receivePacket);
				String sentence = new String(receivePacket.getData());
				System.out.println("Server: " + sentence);
				
				// Server send data to Client
				byte[] sendData = new byte[1024];
				InetAddress ipAddress = receivePacket.getAddress();
				int port = receivePacket.getPort();

				String capitalizedSentence = sentence.toUpperCase();
				sendData = capitalizedSentence.getBytes();
				
				DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, ipAddress, port);
				serverSocket.send(sendPacket);
			}
		}
	}
}
