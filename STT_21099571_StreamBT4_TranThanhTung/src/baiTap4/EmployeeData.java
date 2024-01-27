package baiTap4;

import java.time.LocalDate;
import java.util.List;

public class EmployeeData {
	private static List<Employee> employees;
	
	static {
		employees = List.of(
				new Manager("1001", "Nguyen Van A", LocalDate.of(1999, 12, 12), 10000000),
				new SalariesEmployee("1002", "Nguyen Van B", LocalDate.of(1999, 12, 12), 10000),
				new SalariesEmployee("1003", "Nguyen Van C", LocalDate.of(1999, 12, 12), 20000000),
				new SalariesEmployee("1004", "Lan", LocalDate.of(1999, 12, 12), 10000),
				new HourlyEmployee("1005", "Hoa", LocalDate.of(1999, 12, 12), 10, 100),
				new HourlyEmployee("1006", "Hoa", LocalDate.of(1999, 12, 12), 100, 100)
		);
	}
	
	public static List<Employee> getEmployees() {
		return employees;
	}
}
