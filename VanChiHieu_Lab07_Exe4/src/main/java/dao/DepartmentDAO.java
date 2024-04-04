package dao;

import entities.Department;
import jakarta.persistence.EntityManager;

import java.util.List;

public class DepartmentDAO {
    private EntityManager em;

    public DepartmentDAO(EntityManager em) {
        this.em = em;
    }
    public boolean addDepartment(Department department) {
        try {
            em.getTransaction().begin();
            em.persist(department);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
            return false;
        }
    }

    public boolean updateDepartment(Department department) {
        try {
            em.getTransaction().begin();
            em.merge(department);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
            return false;
        }
    }

    public boolean deleteDepartment(Department department) {
        try {
            em.getTransaction().begin();
            em.remove(department);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
            return false;
        }
    }

    public Department findById(Integer id) {
        Department department = em.find(Department.class, id);
        return department;
    }

    public Department findByName(String name){
        Department department = (Department) em.createNativeQuery("SELECT * FROM Department WHERE Name = ?", Department.class)
                .setParameter(1, name)
                .getSingleResult();

        return department;
    }
    public List<Department> getAll() {
        return em.createQuery("SELECT d FROM Department d", Department.class).getResultList();

    }
}
