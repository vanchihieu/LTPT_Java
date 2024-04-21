package app;

import jakarta.persistence.EntityManager;
import services.EntityManagerFactoryUtil;

public class Test {
    public static void main(String[] args) {
        EntityManagerFactoryUtil entityManagerFactoryUtil = new EntityManagerFactoryUtil();
        EntityManager entityManager = entityManagerFactoryUtil.getEnManager();
    }
}
