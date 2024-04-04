package entity;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name="Treatments")
@IdClass(TreatmentPK.class)
public class Treatment implements Serializable{
	@Id
	@ManyToOne
	@JoinColumn(name="doctorId")
	private Doctor doctor;
	@Id
	@ManyToOne
	@JoinColumn(name = "patientId")
	private Patient patient;
	@Temporal(TemporalType.DATE)
	private Date startDate;
	@Temporal(TemporalType.DATE)
	private Date endDate;
	@Column(columnDefinition = "text")
	private String description;
	
	public Treatment() {
	}
}
