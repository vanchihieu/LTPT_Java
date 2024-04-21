package services;

import java.rmi.Remote;
import java.rmi.RemoteException;

import entities.Message;

// 1. Define RMI Service Interface
public interface MessageService extends Remote {
	
	public String sendMessge(String clientMessage) throws RemoteException;
	
	public Message sendMessage(Message clientMessage) throws RemoteException;
	
//	public boolean insertMessage(Message clientMessage) throws RemoteException;
//	
//	public boolean updateMessage(Message clientMessage) throws RemoteException;
//	
//	public boolean getList() throws RemoteException;
}