package demo;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class CalService extends UnicastRemoteObject implements ICalService{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5966386273951103571L;

	public CalService() throws RemoteException{
	}
	
	@Override
	public int add(int a, int b) throws RemoteException {
		return a + b;
	}

	@Override
	public int sub(int a, int b) throws RemoteException {
		return a - b;
	}

}
