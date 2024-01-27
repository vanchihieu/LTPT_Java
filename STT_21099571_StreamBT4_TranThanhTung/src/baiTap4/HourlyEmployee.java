package baiTap4;

import java.time.LocalDate;
import java.util.function.Supplier;

public class HourlyEmployee extends Employee{
	private int HoursWorked;
	private double HourlyWage;
	
	public double weeklyPay() {
		if(HoursWorked > 40)
			return 40 * HourlyWage + (HoursWorked - 40) * (HourlyWage * 1.5);
		
		return HoursWorked * HourlyWage;
	}
	
	public double weeklyPay(Supplier<Double> s) {
		return s.get();
	}
	
	public HourlyEmployee(String id, String name, LocalDate dob, int hoursWorked, double hourlyWage) {
		super(id, name, dob);
		setHourlyWage(hourlyWage);
		setHoursWorked(hoursWorked);
	}
	public int getHoursWorked() {
		return HoursWorked;
	}
	public void setHoursWorked(int hoursWorked) {
		HoursWorked = hoursWorked;
	}
	public double getHourlyWage() {
		return HourlyWage;
	}
	public void setHourlyWage(double hourlyWage) {
		HourlyWage = hourlyWage;
	}
	
	
	@Override
	public String toString() {
		return super.toString() + " " + HoursWorked + " " + HoursWorked;
	}
	
}
