package db;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Connection {

    private static Connection instance;
    private EntityManagerFactory emf;

    private Connection() {
        // TODO Auto-generated constructor stub
        emf = Persistence.createEntityManagerFactory("JPA_ORM_Student_MSSQL");

    }

    public static Connection getInstance() {
        if (instance == null)
            instance = new Connection();
        return instance;
    }

    public EntityManagerFactory getEmf() {
        return emf;
    }

}
