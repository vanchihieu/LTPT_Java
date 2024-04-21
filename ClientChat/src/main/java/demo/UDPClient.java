package demo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPClient {

	public static void main(String[] args) throws Exception {
		BufferedReader inFromUser = new BufferedReader(
				new InputStreamReader(System.in));
		try (DatagramSocket clientSocket = new DatagramSocket()) {
			InetAddress ipAddress = InetAddress.getByName("localhost");
			
			while(true) {
				// Client send data to server
				byte[] sendData = new byte[1024];
				String sentence = inFromUser.readLine();
				sendData = sentence.getBytes();
				
				DatagramPacket sendPacket = new DatagramPacket(
						sendData, sendData.length, ipAddress, 9876);
				
				clientSocket.send(sendPacket);

				// Client receive data from server
				byte[] receiveData = new byte[1024];
				DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
				clientSocket.receive(receivePacket);
				String modifiedSentence = new String(receivePacket.getData());
				System.out.println("From Server:" + modifiedSentence);
			}
		}
	}
}
