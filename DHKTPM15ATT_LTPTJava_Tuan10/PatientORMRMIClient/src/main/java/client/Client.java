package client;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import dao.IDepartmentDao;

public class Client {
	public static void main(String[] args) throws MalformedURLException, RemoteException, NotBoundException {
		
		IDepartmentDao demaprtmentDao = (IDepartmentDao) Naming.lookup("rmi://DESKTOP-G8E3BC9:9898/demaprtmentDao");
		demaprtmentDao
		.getNumOfDoctorsByDepartments()
		.entrySet()
		.forEach(entry -> {
			System.out.println(entry.getKey() + "\tNumber of doctors: " + entry.getValue());
		});
	}
}
