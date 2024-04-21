package app;




import dao.PatientDao;
import services.PatientService;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;



public class Client {
    public static void main(String[] args) throws MalformedURLException, NotBoundException, RemoteException, ClassNotFoundException{
        Registry registry = LocateRegistry.getRegistry("192.168.150.100", 2008);

		PatientDao patientDao = (PatientDao) registry.lookup("patientService");

        patientDao.listPatients("Dermatology Services", 1, 2021).forEach(x -> System.out.println(x));
        
//        patientDao.getNoOfPatientsBySpeciality("Cardiology").forEach((k, v) -> System.out.println(k + " " + v));
    }
}
