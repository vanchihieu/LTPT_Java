package entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "doctors")
public class Doctor extends Person implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String speciality;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    private Department department;

    @OneToMany(mappedBy = "doctor", fetch = FetchType.LAZY)
    private Set<Treatment> treatments = new HashSet<>();

    public Doctor() {
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "speciality='" + speciality + '\'' +
                ", department=" + department +
                ", treatments=" + treatments +
                '}';
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Set<Treatment> getTreatments() {
        return treatments;
    }

    public void setTreatments(Set<Treatment> treatments) {
        this.treatments = treatments;
    }

    public Doctor(String id, String phone, String name, String speciality) {
        super(id, phone, name);
        this.speciality = speciality;

    }
}