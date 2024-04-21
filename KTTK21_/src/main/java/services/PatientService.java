package services;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.Map;

import dao.PatientDao;
import entities.Patient;
import jakarta.persistence.EntityManager;

public class PatientService extends UnicastRemoteObject implements PatientDao{
	private static final long serialVersionUID = 1L;
	private EntityManager entityManager;
	
	protected PatientService(EntityManager entityManager) throws RemoteException {
		this.entityManager = entityManager;
	}

	/**
	 * Liệt kê danh sách các bệnh nhân (patients) thuộc một chuyên khoa (speciality
– tìm tương đối) nào đó, mà bệnh nhân được điều trị trong tháng/năm nào đó (dựa vào cột 
ngày bắt đầu)
	 */
	// select * from patients p, treatments t where p.id = t.patient_id and t.start_date between '2021-01-01' and '2021-01-31' and p.speciality = 'speciality';
	

//	@SuppressWarnings("unchecked")
	@Override
	public List<Patient> listPatients(String speciality, int month, int year) throws RemoteException {
		return entityManager.createQuery("SELECT p FROM Patient p JOIN p.treatments t WHERE p.speciality = :speciality AND YEAR(t.startDate) = :year AND MONTH(t.startDate) = :month").getResultList();
	}

	@Override
	public Map<String, Long> getNoOfPatientsBySpeciality(String departmentName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updateAddress(String patientID, String newAddress) {
		// TODO Auto-generated method stub
		return false;
	}

}

