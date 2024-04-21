package entity;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name ="id")
public abstract class CommissionEmployee extends Employee {
	private double aaa;
	
	public CommissionEmployee() {
		// TODO Auto-generated constructor stub
	}

}
