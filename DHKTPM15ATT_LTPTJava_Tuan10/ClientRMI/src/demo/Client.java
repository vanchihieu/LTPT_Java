package demo;

import java.rmi.Naming;

public class Client {
	public static void main(String[] args) throws Exception {
		
		//Lookup from RMI registry
		ICalService calService = 
				(ICalService) Naming.lookup("rmi://DESKTOP-G8E3BC9:9090/calService");
		
		//Thuc thi cac remote methods
		int sum = calService.add(100, 50);
		int sub = calService.sub(100, 50);
		System.out.println(sum);
		System.out.println(sub);
		
	}
}
