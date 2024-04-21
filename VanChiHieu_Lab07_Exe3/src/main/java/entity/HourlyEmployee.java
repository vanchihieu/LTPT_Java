package entity;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name ="id")
public class HourlyEmployee extends Employee {
	
	public HourlyEmployee() {
		// TODO Auto-generated constructor stub
	}

}
