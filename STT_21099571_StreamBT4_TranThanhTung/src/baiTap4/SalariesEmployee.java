package baiTap4;

import java.time.LocalDate;

public class SalariesEmployee extends Employee{
	protected double annualSalary;
	
	@Override
	public double weeklyPay() {
		return annualSalary;
	}
	
	public SalariesEmployee(String id, String name, LocalDate dob, double annualSalary) {
		super(id, name, dob);
		setAnnualSalary(annualSalary);
		
	}
	
	public SalariesEmployee() {
		super();
	}

	public double getAnnualSalary() {
		return annualSalary;
	}

	private void setAnnualSalary(double annualSalary) {
		this.annualSalary = annualSalary;
	}
	
	
}
