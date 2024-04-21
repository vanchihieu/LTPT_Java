package app;

import java.rmi.RemoteException;

import jakarta.persistence.EntityManager;
import services.EntityManagerFactoryUtil;
import services.PatientService;

public class Test {
    public static void main(String[] args) throws RemoteException {
        
        EntityManagerFactoryUtil entityManagerFactoryUtil = new EntityManagerFactoryUtil();
        EntityManager entityManager = entityManagerFactoryUtil.getEnManager();
        PatientService patientService = new PatientService(entityManager);
        
//        patientService.listPatients("Dermatology Services", 1, 2021).forEach(e -> System.out.println(e));
//        patientService.getNoOfPatientsBySpeciality("Cardiology").forEach((k, v) -> System.out.println(k + " " + v));
        
    }
}
