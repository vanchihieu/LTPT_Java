package connection;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Connection {
	
	private static Connection instance;
	
	private EntityManagerFactory emf;
	
	public Connection() {
		emf = Persistence.createEntityManagerFactory("chapter4_lab5");
	}
	
	public static Connection getInstance() {
		if(instance == null)
			instance = new Connection();
		return instance;
	}
	
	public EntityManagerFactory getEmf() {
		return emf;
	}

}
