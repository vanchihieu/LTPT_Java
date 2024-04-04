package dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import entity.Project;
import util.HibernateUtil;

public class ProjectDao {

	private SessionFactory sessionFactory;

	public ProjectDao() {
		sessionFactory = HibernateUtil.getInstance().getSessionFactory();
	}

	public boolean addProject(Project project) {

		if(!(project.getStartDate().after(new Date()) && project.getBudget() > 0))
			return false;

		Session session = sessionFactory.getCurrentSession();
		Transaction tr = session.getTransaction();

		try {
			tr.begin();
			session.persist(project);
			tr.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}

		return false;
	}
	
	public Project getProject(String prjId) {
		Project project = null;
		Session session = sessionFactory.getCurrentSession();
		Transaction tr = session.getTransaction();

		try {
			tr.begin();
			project = session.find(Project.class, prjId);
			tr.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}

		return project;
	}

	public List<Project> getProjects() {
		List<Project> projects = null;
		Session session = sessionFactory.getCurrentSession();
		Transaction tr = session.getTransaction();

		try {
			tr.begin();
			String query = "select * from projects";
			projects = session.createNativeQuery(query, Project.class).getResultList();
			tr.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}

		return projects;
	}

}
