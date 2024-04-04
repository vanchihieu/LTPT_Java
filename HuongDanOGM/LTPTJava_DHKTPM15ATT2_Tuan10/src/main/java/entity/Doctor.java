package entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Table;

@Entity
@Table(name="doctors")
@AttributeOverride(name = "name", column = @Column(name="doctor_name"))
public class Doctor extends Person implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7237123911619197578L;
	@ElementCollection(fetch = FetchType.EAGER)
	private Set<String> certifications;
	@Column(nullable = false)
	private String specialty;
	
	public Doctor() {
	}

	public Doctor(String id, String name, Set<String> phones, Set<String> certifications,
			String specialty) {
		super(id, name, phones);
		this.certifications = certifications;
		this.specialty = specialty;
	}

	public Set<String> getCertifications() {
		return certifications;
	}

	public void setCertifications(Set<String> certifications) {
		this.certifications = certifications;
	}

	public String getSpecialty() {
		return specialty;
	}

	public void setSpecialty(String specialty) {
		this.specialty = specialty;
	}

	@Override
	public String toString() {
		return "Doctor [certifications=" + certifications + ", specialty=" + specialty + ", toString()="
				+ super.toString() + "]";
	}

}
