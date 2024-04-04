package app;

import java.rmi.RemoteException;

import dao.DepartmentDao;
import dao.IDepartmentDao;

public class App {
	public static void main(String[] args) throws RemoteException {
		
//		SessionFactory sessionFactory = MySessionFactory.getInstance().getSessionFactory();
		
		IDepartmentDao departmentDao = new DepartmentDao();
		
		departmentDao
		.getNumOfDoctorsByDepartments()
		.entrySet()
		.forEach(entry -> {
			System.out.println(entry.getKey() + "Number of doctors: " + entry.getValue());
		});
		
		
	}
}
