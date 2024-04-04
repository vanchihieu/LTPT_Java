package demo;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;

public class Server {
	public static void main(String[] args) throws Exception {
		//Thiet lap 1 policy file
		//Khoi tao context
		Hashtable<String, String> init = new Hashtable<String, String>();
		init.put("java.security.policy", "policy/policy.policy");
		Context context = new InitialContext(init);
		
		//Mo 1 port
		Registry locateRegistry = LocateRegistry.createRegistry(9090);
		
		//Tao remote java object
		ICalService calService = new CalService();
		
		context.bind("rmi://DESKTOP-G8E3BC9:9090/calService", calService);
		
		System.out.println("Ready...");
		
	}
}




