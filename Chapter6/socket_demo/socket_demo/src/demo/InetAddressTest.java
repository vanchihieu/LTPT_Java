package demo;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class InetAddressTest {

	public static void main(String[] args) throws UnknownHostException {
		InetAddress address = InetAddress.getLocalHost();
		System.out.println(address);
		
		address = InetAddress.getByName("www.google.com");
		System.out.println(address);
		
		InetAddress[] names = InetAddress.getAllByName("www.google.com");
		for (int i=0; i<names.length; i++) {
			System.out.println(names[i]);
		}
	}
}
