package entity;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name ="id")

public class SalariedEmployee extends Employee{
	
	private double weeklySalary;
	
	public SalariedEmployee() {
		// TODO Auto-generated constructor stub
	}

}
