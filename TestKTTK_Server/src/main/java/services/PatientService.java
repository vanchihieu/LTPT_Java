package services;

import dao.PatientDao;
import entities.Patient;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PatientService extends UnicastRemoteObject implements PatientDao{
	private static final long serialVersionUID = 1L;
	private EntityManager entityManager;
	
	public PatientService(EntityManager entityManager) throws RemoteException {
		this.entityManager = entityManager;
	}

	/**
	 * Liệt kê danh sách các bệnh nhân (patients) thuộc một chuyên khoa (speciality
– tìm tương đối) nào đó, mà bệnh nhân được điều trị trong tháng/năm nào đó (dựa vào cột 
ngày bắt đầu)
	 */

@SuppressWarnings("unchecked")
	// SELECT * FROM patients p
// JOIN treatments t ON p.person_id = t.patient_id
// JOIN doctors d ON t.doctor_id = d.person_id
// WHERE d.speciality like "Dermatology Services"
// AND YEAR(t.start_date) = "2021" AND MONTH(t.start_date) = "01"
//	@SuppressWarnings("unchecked")
	@Override
	public List<Patient> listPatients(String speciality, int month, int year) throws RemoteException {
		return entityManager.createNativeQuery("SELECT p.* FROM patients p \r\n"
				+ "JOIN treatments t ON p.person_id = t.patient_id \r\n"
				+ "JOIN doctors d ON t.doctor_id = d.person_id \r\n"
				+ "WHERE d.speciality like :speciality \r\n"
				+ " AND YEAR(t.start_date) = :year AND MONTH(t.start_date) = :month", Patient.class)
				.setParameter("speciality", "%"+speciality+"%")
				.setParameter("year", year)
				.setParameter("month", month)
				.getResultList();
//		return entityManager.createQuery(
//				"SELECT p FROM Patient p JOIN p.treatments t JOIN t.doctor d WHERE d.speciality = :speciality AND YEAR(t.startDate) = :year AND MONTH(t.startDate) = :month")
//				.setParameter("speciality", speciality).setParameter("year", year).setParameter("month", month)
//				.getResultList();
	}

	// hống kê số bệnh nhân theo từng chuyên khoa (speciality) của một khoa (department) nào đó khi biết tên khoa.
//	SELECT d.speciality, COUNT(p.person_id)
//	FROM patients p
//	JOIN treatments t ON p.person_id = t.patient_id
//	JOIN doctors d ON t.doctor_id = d.person_id
//	JOIN departments dp ON d.department_id = dp.department_id
//	WHERE dp.name = "Cardiology"
//	GROUP BY d.speciality
	@Override
	public Map<String, Long> getNoOfPatientsBySpeciality(String departmentName) throws RemoteException {
		Query query = entityManager.createNativeQuery(""
				+ "SELECT d.speciality, COUNT(p.person_id) AS total_patients \r\n"
				+ "FROM patients p JOIN treatments t ON p.person_id = t.patient_id \r\n"
				+ "JOIN doctors d ON t.doctor_id = d.person_id \r\n"
				+ "JOIN departments dp ON d.department_id = dp.department_id \r\n"
				+ "WHERE dp.name = :departmentName \r\n"
				+ "GROUP BY d.speciality");
				query.setParameter("departmentName", departmentName);
		
		List<Object[]> result = query.getResultList();
		Map<String, Long> resultMap = new HashMap<>();
		for(Object[] obj : result) {
			resultMap.put((String)obj[0], (Long)obj[1]);
		}

		return resultMap;
	}

//	Cập nhật lại địa chỉ (address) của bệnh nhân khi biết mã bệnh nhân
//(person_id) (không cho phép cập nhật các thông tin khác của bệnh nhân)
	@Override
	public boolean updateAddress(String patientID, String newAddress) {
		EntityTransaction trans = entityManager.getTransaction();
		try {
			trans.begin();
			Patient patient = entityManager.find(Patient.class, patientID);
			patient.setAddress(newAddress);
			entityManager.merge(patient);

			trans.commit();
			return true;
		}catch (Exception e){
			if (trans.isActive()){
				trans.rollback();
			}
			e.printStackTrace();

			return false;
		}
	}

}

