package baiTap4;

import java.text.DecimalFormat;
import java.util.HashSet;
import java.util.List;

public class PayRollApp {
	public static void main(String[] args) {
		List<Employee> data = EmployeeData.getEmployees();
		EmployeeList list = new EmployeeList();
		
		list.addAll(new HashSet<>(data));
		
		list.getTotalWeeklySalary()
		.entrySet()
		.forEach(entry -> {
			System.out.println(entry.getKey() + ": " + new DecimalFormat("#,##0").format(entry.getValue()));
		});
		
//		list.updateHourlyWorked("1008", 120);
		
//		list.sortByWeeklySalary2().forEach(System.out::println);
		
//		double salary = list.getWeeklyTotalSalaryOfManager();
//		System.out.println("Salary: " + salary);
		
//		list.getEmployees().forEach(System.out::println);
		
	}
}
