package baiTap4;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.Objects;

public abstract class Employee {
	protected String id, name;
	protected LocalDate dob;
	
	public Employee(String id, String name, LocalDate dob) {
		setId(id);
		setName(name);
		setDob(dob);
	}
	
	public Employee() {
		
	}
	
	public long getYear() {
		return dob.getYear();
	}
	
	
	public String getId() {
		return id;
	}
	private void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public LocalDate getDob() {
		return dob;
	}
	public void setDob(LocalDate dob) {
		this.dob = dob;
	}



	@Override
	public int hashCode() {
		return Objects.hash(id);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		return Objects.equals(id, other.id);
	}


	public abstract double weeklyPay();
	
	@Override
	public String toString() {
		return String.format("%-10s %-20s %-10s %-10s", id, name, dob, new DecimalFormat("#,##0").format(weeklyPay()));
	}
}
