package app;

import jakarta.persistence.EntityManager;
import services.EntityManagerFactoryUtil;
import services.PatientService;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ServerRMI {
    public static void main(String[] args) throws RemoteException, AlreadyBoundException {
        // create a registry
        Registry registry = LocateRegistry.createRegistry(2008);

        EntityManagerFactoryUtil entityManagerFactory = new EntityManagerFactoryUtil();
        EntityManager entityManager = entityManagerFactory.getEnManager();

        PatientService patientService = new PatientService(entityManager); // Remote Object

        registry.bind("patientService", patientService);

        System.out.println("RMI Server ready");

    }
}
