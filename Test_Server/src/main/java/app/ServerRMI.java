package app;

import jakarta.persistence.EntityManager;
import services.EntityManagerFactoryUtil;
import services.PatientService;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ServerRMI {
    public static void main(String[] args) throws RemoteException {
        // create a registry
        Registry registry = LocateRegistry.createRegistry(2006);

        EntityManagerFactoryUtil entityManagerFactory = new EntityManagerFactoryUtil();
        EntityManager entityManager = entityManagerFactory.getEnManager();

        PatientService patientService = new PatientService(entityManager); // Remote Object

        registry.rebind("patientService", patientService);

        System.out.println("RMI Server ready");

    }
}
