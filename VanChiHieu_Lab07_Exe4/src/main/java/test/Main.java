package test;

import dao.DepartmentDAO;
import dao.EntityManagerFactoryUtil;
import entities.Department;
import entities.Instructor;
import entities.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.time.LocalDateTime;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactoryUtil mangerFactoryUtil = new EntityManagerFactoryUtil();

        EntityManager entityManager = mangerFactoryUtil.getEnManager();

        DepartmentDAO departmentDAO = new DepartmentDAO(entityManager);

//        Department result = departmentDAO.findById(1);
//        System.out.println(result);

//        Department name = departmentDAO.findByName("Engineering");
//        System.out.println(name);

        List<Department> getAll = departmentDAO.getAll();
        getAll.forEach(System.out::println);

//        EntityManagerFactory emf = Persistence.createEntityManagerFactory("mariadb");
//        EntityManager em = emf.createEntityManager();


//            EntityTransaction tx = em.getTransaction();
//            tx.begin();

//            // Create a new Student
//            Student student = new Student("John", "Doe", LocalDateTime.now());
//            em.persist(student);
//
//            // Create a new Instructor
//            Instructor instructor = new Instructor("Jane", "Doe", LocalDateTime.now());
//            em.persist(instructor);
//
//            tx.commit();


    }
}
