package service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class EntityManagerFactoryUtil {
    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;

    public EntityManagerFactoryUtil() {
        entityManagerFactory = Persistence.createEntityManagerFactory("hieu");
        entityManager = entityManagerFactory.createEntityManager();
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void closeEntityManagerFactory() {
        entityManagerFactory.close();
    }

    public void closeEntityManager() {
        entityManager.close();
    }
}
