package test;

import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import dao.DoctorDao;
import dao.PatientDao;
import entity.Doctor;
import entity.Patient;
import entity.Treatment;

public class MainTest {
	public static void main(String[] args) {
		PatientDao patientDao = new PatientDao();
		DoctorDao doctorDao = new DoctorDao();
		
		Doctor doctor1 = new Doctor("DT100", "Steven King",new HashSet<>(Arrays.asList("(155) 555-2234")), new HashSet<String>(Arrays.asList("CCS-P", "CPCT", "CNA")), "Dermatologist");
		Doctor doctor2 = new Doctor("DT101", "Neena Kochhar",new HashSet<>(Arrays.asList("(355) 444-1234")), new HashSet<String>(Arrays.asList("CPT","CNA","RBT","CPC")),"Gastroenterologist");
		Doctor doctor3 = new Doctor("DT102", "Elliot Reid",new HashSet<>(Arrays.asList("(355) 444-98765")), new HashSet<String>(Arrays.asList("RBT","CPC","CPhT")),"Ophthalmologist");
		Doctor doctor4 = new Doctor("DT103", "Keith De Haan",new HashSet<>(Arrays.asList("(369) 654-9299")), new HashSet<String>(Arrays.asList("CPhT","CCMA")),"Surgical Attending Physician");
		Doctor doctor5 = new Doctor("DT104", "Lex Dudemeister",new HashSet<>(Arrays.asList("(429) 690-9229")), new HashSet<String>(Arrays.asList("CCS-P","CCMA")),"Infectious disease");
		Doctor doctor6 = new Doctor("DT106", "Louise Doran",new HashSet<>(Arrays.asList("(650) 507-9833","(650) 505-3876")), new HashSet<String>(Arrays.asList("CCS-P","CCMA","CPhT")),"Cardiologists");
		
//		doctorDao.addDoctor(doctor1);
//		doctorDao.addDoctor(doctor2);
//		doctorDao.addDoctor(doctor3);
//		doctorDao.addDoctor(doctor4);
//		doctorDao.addDoctor(doctor5);
//		doctorDao.addDoctor(doctor6);
		
		Patient patient = new Patient("PT-1", "Shenna Espinoza",  new HashSet<>(Arrays.asList("(555) 857-118")), new Date(2020-1900, 1, 1), "66-4 Parkhurst Rd, Chelmsford MA 1824");
		Set<Treatment> treatments  = new HashSet<Treatment>(Arrays.asList(new Treatment(doctor1, new Date(2019 - 1900, 1, 15), new Date(2019 - 1900, 1, 15), "Circadian rhythm sleep disorder")));
		patient.setTreatments(treatments  );
		
		Patient patient2 = new Patient("PT-2", "Carola Johns", new HashSet<>(Arrays.asList("(141) 159-448")), new Date(1955-1900, 5, 10), "30 Memorial Drive, Avon MA 2322");
		Set<Treatment> treatments2  = new HashSet<Treatment>(Arrays.asList(new Treatment(doctor3, new Date(2020 - 1900, 3, 25), new Date(2020 - 1900, 3, 25), "Candida parapsilosis")));
		patient2.setTreatments(treatments2);
		
		Patient patient3 = new Patient("PT-3", "Steven King",  new HashSet<>(Arrays.asList("(515) 123-4567")), new Date(1965 - 1900, 5, 15), "246 College Dr.Middletown, NY 10940");
		Set<Treatment> treatments3  = new HashSet<Treatment>(Arrays.asList(new Treatment(doctor5, new Date(2020 - 1900, 3, 10), new Date(2020 - 1900, 3, 10), "Headaches")));
		patient3.setTreatments(treatments3);
		
		Patient patient4 = new Patient("PT-4", "Carola Johns", new HashSet<>(Arrays.asList("(141) 159-448")), new Date(1955 - 1900, 5, 10), "252 North Lafayette St.Bronx, NY 10468");
		Set<Treatment> treatments4  = new HashSet<Treatment>(Arrays.asList(new Treatment(doctor5, new Date(2021 - 1900, 3, 10), new Date(2021 - 1900, 3, 10), "Headaches"), new Treatment(doctor3, new Date(2020 - 1900, 3, 2), new Date(2020 - 1900, 3, 2), "Stomach Aches")));
		patient4.setTreatments(treatments4);
		
		Patient patient5 = new Patient("PT-5", "Michael Hartstein",  new HashSet<>(Arrays.asList("515.123.7777", "515.123.8181")), new Date(1995-1900, 2, 10), "688 Amerige St.Bronx, NY 10453");
		Set<Treatment> treatments5  = new HashSet<Treatment>(Arrays.asList(new Treatment(doctor6, new Date(2021 - 1900, 3, 12), new Date(2021 - 1900, 3, 12), "Stomach Aches"),new Treatment(doctor6, new Date(2022 - 1900, 1, 2), new Date(2022 - 1900, 1, 3), "Colds and Flu")));
		patient5.setTreatments(treatments5);
		
//		patientDao.addPatient(patient);
//		patientDao.addPatient(patient2);
//		patientDao.addPatient(patient3);
//		patientDao.addPatient(patient4);
//		patientDao.addPatient(patient5);
		
		patientDao.getPatients().forEach(dp -> System.out.println(dp));
		doctorDao.getDoctors().forEach(dp -> System.out.println(dp));
	}
}
