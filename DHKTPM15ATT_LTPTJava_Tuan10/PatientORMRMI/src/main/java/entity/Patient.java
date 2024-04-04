package entity;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="Patients")
@AttributeOverride(name = "id", column = @Column(name="patientId"))
public class Patient extends Person implements Serializable{
	private String gender;
	@Temporal(TemporalType.DATE)
	private Date dateOfBirth;
	
	public Patient() {
	}
}
