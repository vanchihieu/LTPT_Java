package baiTap4;

import java.time.LocalDate;

public class Manager extends SalariesEmployee {
	private double weeklyBonus;
	
	public double getWeeklyBonus() {
		return weeklyBonus;
	}
	public void setWeeklyBonus(double weeklyBonus) {
		this.weeklyBonus = weeklyBonus;
	}
	
	@Override
	public double weeklyPay() {
		return super.getAnnualSalary() + weeklyBonus;
	}

	public Manager(String id, String name, LocalDate dob, double weeklyBonus) {
		super(id, name, dob, weeklyBonus);
	}
	
	public Manager() {
		super();
	}
}
