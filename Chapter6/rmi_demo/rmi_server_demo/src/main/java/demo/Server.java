package demo;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import services.MessageServiceImpl;

//3. Create RMI Server program
public class Server {

	public static void main(String[] args) throws RemoteException, AlreadyBoundException {
		
		// Instantiating the implementation class 
		MessageServiceImpl skeletonMessage = new MessageServiceImpl();	
		
		// Binding the remote object (stub) in the registry
		// RMI 1099
		Registry registry = LocateRegistry.createRegistry(2004);
		
		registry.bind("MessageService", skeletonMessage);
		
		
		System.out.println("Server ready");
	}

}
