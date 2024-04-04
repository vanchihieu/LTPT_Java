package dao;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Map;

import entity.Department;

public interface IDepartmentDao extends Remote{
	public Map<Department, Integer> getNumOfDoctorsByDepartments () throws RemoteException;
}
