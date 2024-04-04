package app;

import db.Connection;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;

public class Demo {
    public static void main(String[] args) {

        EntityManagerFactory emf = Connection.getInstance().getEmf();
        EntityManager em = emf.createEntityManager();

    }
}
