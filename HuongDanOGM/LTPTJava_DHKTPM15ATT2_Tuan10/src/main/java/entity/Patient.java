package entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Table;

@Entity
@Table(name="patients")
@AttributeOverride(name="name", column = @Column(name="patient_name"))
public class Patient extends Person implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5705732190782176522L;
	@Column(name="date_of_birth")
	private Date DOB;
	private String address;
	
	
	@ElementCollection(fetch = FetchType.EAGER)
	private Set<Treatment> treatments;
	
	public Patient() {
	}

	public Patient(String id, String name,  Set<String> phones, Date dOB, String address) {
		super(id, name,  phones);
		DOB = dOB;
		this.address = address;
	}



	public Date getDOB() {
		return DOB;
	}

	public void setDOB(Date dOB) {
		DOB = dOB;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Set<Treatment> getTreatments() {
		return treatments;
	}

	public void setTreatments(Set<Treatment> treatments) {
		this.treatments = treatments;
	}

	@Override
	public String toString() {
		return "Patient [DOB=" + DOB + ", address=" + address + ", treatments=" + treatments  + ", toString()="
				+ super.toString() + "]";
	}
}
