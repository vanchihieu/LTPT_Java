package services;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class EntityManagerFactoryUtil {
    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;

    public EntityManagerFactoryUtil() {
        this.entityManagerFactory = Persistence.createEntityManagerFactory("mariadb");
        this.entityManager = entityManagerFactory.createEntityManager();
    }

    public EntityManager getEnManager(){
        return entityManager;
    }

    public void closeEntityManagerFactory(){
        entityManagerFactory.close();
    }

    public  void closeEntityManager(){
        entityManager.close();
    }
}
