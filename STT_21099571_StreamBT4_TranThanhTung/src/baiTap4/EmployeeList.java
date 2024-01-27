package baiTap4;
/*
 * @ (#) Company.java       1.0     Sep 11, 2023
 *
 * Copyright (c) 2023 IUH. All rights reserved.
 */



import java.time.LocalDate;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class EmployeeList {
	
	private Set<Employee> employees;

	/**
	 * Default constructor: Create a collection of employees
	 */
	public EmployeeList() {
		employees = new HashSet<>();
	}

	/**
	 * This method returns the collection of employees
	 * @return
	 */
	public Set<Employee> getEmployees() {
		return employees;
	}

	/**
	 * This method adds a new employee to the list. 
	 * @param emp the new employee
	 * @throws IllegalArgumentException if the employee id is duplicated
	 */
	public boolean addEmployee(Employee emp) {
		return employees.add(emp);
//		return (employees.contains(emp)) ?  false : employees.add(emp);
	}

	/**
	 * Search the employee by id
	 * @param id
	 * @return
	 */
	public Employee searchEmployeeById(String id) {
		return employees.stream()
						.filter(e -> e.getId().equalsIgnoreCase(id))
						.findFirst()
						.get();
	}

	/**
	 * This method adds an array of employees to the list
	 * @param emps the array of employees
	 */
	public void addAll(Set<Employee> emps) {
		employees.addAll(emps);
	}
	
	/**
	 * This method sorts the list by weekly salary of employees in ascending order
	 * @return
	 */
	public Set<Employee> sortByWeeklySalary() {
		return employees.stream()
//						.sorted((e1, e2) -> Double.compare(e1.weeklyPay(), e2.weeklyPay()))
					    .sorted(Comparator.comparing(Employee::weeklyPay))
						.collect(Collectors.toCollection(LinkedHashSet::new));
	}
	public Set<Employee> sortByWeeklySalary2() {
		return employees.stream()
				.collect(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(Employee::weeklyPay))));
	}
	
	/**
	 * Remove an employee by id
	 * @param id the given id
	 * @throws IllegalArgumentException if the employee with the given id does not exist
	 */
	public boolean removeEmployee(String id) {
		return employees.removeIf(e -> e.getId().equalsIgnoreCase(id));
	}
	
	/**
	 * This method updates the information of an employee by id
	 * @param newInfor the new information
	 * @throws IllegalArgumentException if the employee with the given id does not exist
	 */
	public boolean updateEmployee(Employee newInfor) {
		if(employees.contains(newInfor))
			return false;
		
		employees.stream()
				 .filter(e -> e.getId().equalsIgnoreCase(newInfor.getId()))
				 .forEach(e -> {
					 e.setDob(newInfor.getDob());
					 e.setName(newInfor.getName());
				 });
				 
		return true;
	}
	/**
	 * This method returns an array of employees who are hourly employees and work more than 40 hours per week
	 * @return an array of employees who are hourly employees and work more than 40 hours per week
	 */
	public Set<Employee> getHourlyEmpsWorkMoreThan40() {
		
		return employees.stream()
				        .filter(e -> e instanceof HourlyEmployee)
						.map(e -> (HourlyEmployee) e)
						.filter(e -> e.getHoursWorked() > 40)
						.collect(Collectors.toSet());
	}
	
	/**
	 * This method returns the total salary of all managers
	 * @return the total salary of all managers
	 */
	public double getWeeklyTotalSalaryOfManager () {
		return employees.stream()
				        .filter(e -> e instanceof Manager)
						.map(e -> (Manager) e)
						.mapToDouble(e -> e.weeklyPay())
						.sum();
	}
	
	/**
	 * This method updates the hourly work of an hourly employee by id
	 * @param id the given id
	 * @param newHour the new hourly worked
	 * @throws IllegalArgumentException if the new hourly worked is less than 0
	 * @throws IllegalArgumentException if the employee with the given id does not exist or the employee is not an hourly employee
	 */
	public void updateHourlyWorked(String id, int newHour) {
		
		employees.stream()
				 .filter(e -> e instanceof HourlyEmployee)
				 .filter(e -> e.getId().equalsIgnoreCase(id))
				 .map(e -> (HourlyEmployee) e)		 
				 .forEach(e -> {
					 e.setHoursWorked(newHour);
				 });
	}

	/**
	 * This method returns an array of employees who are young managers (less than 30 years old)
	 * @return an array of employees who are young managers (less than 30 years old)
	 */
	public Set<Employee> getYoungEmployeesAsManagers() {
		return employees.stream()
						.filter(e -> LocalDate.now().getYear() - e.getDob().getYear() < 30)
						.collect(Collectors.toSet());
	}
	
	/**
	 * This method returns a map of number of employees by year of birth
	 * @return
	 */
	public Map<Integer, Long> getNoOfEmployeesByYOB() {
		return employees.stream()
						.map(e -> e.getDob().getYear())
						.collect(Collectors.groupingBy(e -> e, Collectors.counting()));
	}
	
//	+ getTotalWeeklySalary(): Map<String, Double>
	
	public Map<String, Double> getTotalWeeklySalary() {
		return employees.stream()
		.collect(Collectors.groupingBy(e -> e.getClass().getSimpleName(), Collectors.summingDouble(Employee::weeklyPay)))
		.entrySet()
		.stream()
		.sorted(Map.Entry.comparingByValue())
		.collect(Collectors.toMap(Entry::getKey, Entry::getValue, (x, y) -> x, LinkedHashMap::new));
	}
}



