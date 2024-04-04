package dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import entity.Assignment;
import util.HibernateUtil;

public class AssignmentDao {

	private SessionFactory sessionFactory;

	public AssignmentDao() {
		sessionFactory = HibernateUtil.getInstance().getSessionFactory();
	}

	public boolean addAssignment(Assignment assignment) {
		boolean rs = false;
		Session session = sessionFactory.getCurrentSession();
		Transaction tr = session.getTransaction();

		try {
			tr.begin();
			session.merge(assignment);
			tr.commit();
			rs = true;
		} catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}

		return rs;
	}

	public Assignment getAssignment(String empId, String prjId) {
		Assignment ass = null;
		Session session = sessionFactory.getCurrentSession();
		Transaction tr = session.getTransaction();

		try {
			tr.begin();
			String sql = "select * from [dbo].[assignments] where [emp_id] = :x and [prj_id] = :y";
			List<Assignment> temp = session.createNativeQuery(sql, Assignment.class)
					.setParameter("x", empId)
					.setParameter("y", prjId)
					.getResultList();
			tr.commit();
			ass = temp.size() > 0 ? temp.get(0) : null;
		} catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}

		return ass;
	}

	public List<Assignment> getAssignments() {
		List<Assignment> assignments = null;
		Session session = sessionFactory.getCurrentSession();
		Transaction tr = session.getTransaction();

		try {
			tr.begin();
			String query = "Select * from assignments";
			assignments = session.createNativeQuery(query, Assignment.class).getResultList();
			tr.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}

		return assignments;
	}

}
