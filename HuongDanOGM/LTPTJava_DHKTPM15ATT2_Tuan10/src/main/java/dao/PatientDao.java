package dao;

import java.util.List;

import org.hibernate.Transaction;
import org.hibernate.ogm.OgmSession;
import org.hibernate.ogm.OgmSessionFactory;

import entity.Patient;
import util.HibernateUtil;

public class PatientDao {
	
	private OgmSessionFactory sessionFactory;
	
	public PatientDao() {
		sessionFactory = HibernateUtil.getInstance().getSessionFactory();
	}
	
	public boolean addPatient(Patient patient) {
		OgmSession session = sessionFactory.getCurrentSession();

		Transaction tr = session.getTransaction();

		try {
			tr.begin();
			
			session.save(patient);
			
			tr.commit();
		} catch (Exception e) {
			tr.rollback();
		}
		
		return false;
	}
	
	public List<Patient> getPatients() {
		
		OgmSession session = sessionFactory.getCurrentSession();

		Transaction tr = session.getTransaction();

		try {
			tr.begin();
			
			String query = "db.patients.find({})";
			
			List<Patient> patients  = session.createNativeQuery(query, Patient.class).getResultList();
			
			tr.commit();
			
			return patients;
		} catch (Exception e) {
			tr.rollback();
		}
		
		return null;
	}
	
}
