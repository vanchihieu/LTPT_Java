package entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;


@Entity
@Table(name = "treatments")
public class Treatment implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String diagnosis;
    @Column(name = "start_date")
    private LocalDate startDate;
    @Column(name = "end_date")
    private LocalDate endDate;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id")
    private Patient patient;

    public Treatment() {
    }

    public Treatment(String diagnosis, LocalDate startDate, LocalDate endDate, Doctor doctor, Patient patient) {
        this.diagnosis = diagnosis;
        this.startDate = startDate;
        this.endDate = endDate;
        this.doctor = doctor;
        this.patient = patient;
    }

    @Override
    public String toString() {
        return "Treatment{" +
                "diagnosis='" + diagnosis + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", doctor=" + doctor +
                ", patient=" + patient +
                '}';
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
}