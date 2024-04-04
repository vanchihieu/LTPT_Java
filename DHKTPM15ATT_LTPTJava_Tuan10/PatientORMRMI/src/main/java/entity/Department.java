package entity;

import jakarta.persistence.Table;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
@Table(name="Departments")
public class Department implements Serializable {
	@Id
	@Column(name="departmentId")
	private String id;
	private String location;
	private String name;
	@OneToMany(mappedBy = "department")
	private List<Doctor> doctors;
	
	public Department() {
	}

	public Department(String id, String location, String name) {
		super();
		this.id = id;
		this.location = location;
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Doctor> getDoctors() {
		return doctors;
	}

	public void setDoctors(List<Doctor> doctors) {
		this.doctors = doctors;
	}

	@Override
	public String toString() {
		return "Department [id=" + id + ", location=" + location + ", name=" + name +  "]";
	}
	
	
}
