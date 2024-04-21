package app;

import java.rmi.RemoteException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import jakarta.persistence.EntityManager;
import services.EntityManagerFactoryUtil;
import services.PatientService;

public class PatientServiceTest {
	private static EntityManagerFactoryUtil mangerFactoryUtil;
	private static EntityManager entityManager;
	
	@BeforeAll
	static void setup() {
		mangerFactoryUtil = new EntityManagerFactoryUtil();
		entityManager = mangerFactoryUtil.getEnManager();
	}
	
	@Test
	@DisplayName("listPatients")
	void listPatients() throws RemoteException {
		 PatientService patientService = new PatientService(entityManager);
//		 patientService.listPatients("Dermatology Services", 1, 2021).forEach(x ->
//		 System.out.println());
//		 assert(patientService.listPatients("Dermatology Services", 1, 2021) != null);
		 assert(patientService.listPatients("Dermatology Services", 1, 2021).size() > 0);
	}
	
	@AfterAll
	public static void afterAll() {
		mangerFactoryUtil.closeEnManager();
		mangerFactoryUtil.closeEnManagerFactory();
	}
}
