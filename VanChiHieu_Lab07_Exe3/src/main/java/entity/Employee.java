package entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Employee {
	private String firstName;
	private String lastName;
	@Id
	private int id;
	private String socialSecurityNumber;
	
	public Employee() {
		// TODO Auto-generated constructor stub
	}
	

}
