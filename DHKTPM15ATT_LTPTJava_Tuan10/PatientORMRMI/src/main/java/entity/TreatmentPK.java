package entity;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Embeddable;

@Embeddable
public class TreatmentPK implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6476871252268972474L;
	private String doctor;
	private String patient;
	
	public TreatmentPK() {
	}

	@Override
	public int hashCode() {
		return Objects.hash(doctor, patient);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TreatmentPK other = (TreatmentPK) obj;
		return Objects.equals(doctor, other.doctor) && Objects.equals(patient, other.patient);
	}
	
	
}
