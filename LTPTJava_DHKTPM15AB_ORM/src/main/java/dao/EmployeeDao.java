package dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import entity.Employee;
import util.HibernateUtil;

public class EmployeeDao {

	private SessionFactory sessionFactory;

	public EmployeeDao() {
		sessionFactory = HibernateUtil.getInstance().getSessionFactory();
	}

	public boolean addEmployee(Employee employee) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tr = session.getTransaction();
		try {
			tr.begin();
			session.persist(employee);
			tr.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}

		return false;
	}

	public Employee getEmployee(String id) {
		Employee emp = null;
		Session session = sessionFactory.getCurrentSession();
		Transaction tr = session.getTransaction();
		try {
			tr.begin();
			emp = session.find(Employee.class, id);
			tr.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}

		return emp;
	}

	public List<Employee> getEmployees() {
		List<Employee> employees = null;
		Session session = sessionFactory.getCurrentSession();
		Transaction tr = session.getTransaction();

		try {
			tr.begin();
			String query = "Select * from employees";
			employees = session.createNativeQuery(query, Employee.class).getResultList();
			tr.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}

		return employees;
	}

}
