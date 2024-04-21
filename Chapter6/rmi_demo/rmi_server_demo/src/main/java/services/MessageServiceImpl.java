package services;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import entities.Message;

// 2. Implement RMI Service Interface
public class MessageServiceImpl extends UnicastRemoteObject implements MessageService {

	private static final long serialVersionUID = 1L;

	public MessageServiceImpl() throws RemoteException {
	}

	@Override
	public String sendMessge(String clientMessage) throws RemoteException {
		String serverMessage = null;
		if (clientMessage.equals("Client Message")) {
			serverMessage = "Server Message OK 111111111";
		}
		
		return serverMessage;
	}

	@Override
	public Message sendMessage(Message clientMessage) throws RemoteException {
		Message serverMessage = null;
		
		if (clientMessage.getMessageText().equals("Client Message")) {
			serverMessage = new Message("Server Message Object");
		}
		
		return serverMessage;
	}
	
}
