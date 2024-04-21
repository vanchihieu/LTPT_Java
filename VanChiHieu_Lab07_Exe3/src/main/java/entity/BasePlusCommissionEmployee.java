package entity;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name ="id")
public class BasePlusCommissionEmployee extends CommissionEmployee {

	
	public BasePlusCommissionEmployee() {
		// TODO Auto-generated constructor stub
	}
}
