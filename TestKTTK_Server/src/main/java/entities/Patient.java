package entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "patients")
public class Patient extends Person implements Serializable {



	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Enumerated(EnumType.STRING)
	private Gender gender;
	@Column(name = "date_of_birth")
	private String dateOfBirth;
	private String address;

	@OneToMany(mappedBy = "patient")
	private Set<Treatment> treatments;

	public Patient() {
	}



	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	@Override
	public String toString() {
		return "Patient [gender=" + gender + ", dateOfBirth=" + dateOfBirth + ", address=" + address + ", id=" + id + ", phone=" + phone + ", name=" + name + "]";
	}
	
	
	
	
}