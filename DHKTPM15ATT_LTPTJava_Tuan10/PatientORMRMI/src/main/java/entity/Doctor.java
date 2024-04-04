package entity;

import java.io.Serializable;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="Doctors")
@AttributeOverride(name = "id", column = @Column(name="doctorId"))
public class Doctor extends Person implements Serializable{
	private String email;
	private String specialty;
	
	@ManyToOne
	@JoinColumn(name="departmentId")
	private Department department;
	
	public Doctor() {
	}
	
	
}
