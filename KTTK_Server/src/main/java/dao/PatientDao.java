package dao;

import entities.Patient;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;

public interface PatientDao extends Remote{
	public List<Patient> listPatients(String speciality, int month, int year) throws RemoteException;
	public Map<String, Long> getNoOfPatientsBySpeciality(String departmentName) throws RemoteException;
	public boolean updateAddress(String patientID, String newAddress) throws RemoteException;
}
