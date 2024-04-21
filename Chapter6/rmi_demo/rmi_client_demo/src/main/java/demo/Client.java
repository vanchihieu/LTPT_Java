package demo;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import entities.Message;
import services.MessageService;

public class Client {

	public static void main(String[] args) throws RemoteException, NotBoundException {
		// Getting the registry
		Registry registry = LocateRegistry.getRegistry("127.0.0.1", 2004);
		
		// Looking up the registry for the remote object
		MessageService stub = (MessageService) registry.lookup("MessageService");
		
		// Calling the remote method using the obtained object
		String responseMessage = stub.sendMessge("Client Message"); 
		
		System.out.println(responseMessage);
		
		Message responseObject = stub.sendMessage(new Message("Client Message"));
		
		System.out.println(responseObject);
	}

}
