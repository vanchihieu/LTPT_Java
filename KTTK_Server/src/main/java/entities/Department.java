package entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "departments")
public class Department implements Serializable{
	
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
    @Column(name = "department_id")
    private String id;
    private String name;
    private String location;

    @OneToMany(mappedBy = "department", fetch = FetchType.LAZY)
    private Set<Doctor> doctors = new HashSet<>();

    public Department() {
    }

    public Department(String id, String name, String location, Set<Doctor> doctors) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.doctors = doctors;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Set<Doctor> getDoctors() {
        return doctors;
    }

    public void setDoctors(Set<Doctor> doctors) {
        this.doctors = doctors;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", doctors=" + doctors +
                '}';
    }
}