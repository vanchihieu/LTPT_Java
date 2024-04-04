package app;

import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import dao.AssignmentDao;
import dao.EmployeeDao;
import dao.ProjectDao;
import entity.Assignment;
import entity.Employee;
import entity.Project;

public class MainTest {
	public static void main(String[] args) {
		
		ProjectDao projectDao = new ProjectDao();
		EmployeeDao employeeDao = new EmployeeDao();
		AssignmentDao assignmentDao = new AssignmentDao();
		
		Employee emp1 = new Employee("EMP1", "John Smith","john.smith@gmail.com", new Date(2000 - 1900, 1, 1), new HashSet<String>(Arrays.asList("09 456-789", "09 345-567")));
		Employee emp2 = new Employee("EMP2", "John Tran","john.tran@gmail.com", new Date(2010 - 1900, 1, 1), new HashSet<String>(Arrays.asList("09 789-789", "09 345-890","34 456-764")));
		emp2.setManager(emp1);
		employeeDao.addEmployee(emp1 );
		employeeDao.addEmployee(emp2 );
		
		Employee employeeTemp = employeeDao.getEmployee("EMP2");
		Employee employeeTemp2 = employeeDao.getEmployee("EMP20");
		System.out.println(employeeTemp);
		System.out.println(employeeTemp2);
		
		Project prj1 = new Project("PRJ01", "Database System", "UAS", new Date(2022 - 1900, 10, 1) , 987.45);
		Project prj2 = new Project("PRJ02", "Laser Printers", "UAS", new Date(2022 - 1900, 11, 1) , 387.50);
		
		projectDao.addProject(prj1);
		projectDao.addProject(prj2);
		
		Project projectTemp = projectDao.getProject("PRJ01");
		System.out.println(projectTemp);
		
		Assignment assignment1 = new Assignment(emp1, prj1, 15);
		Assignment assignment2 = new Assignment(emp1, prj2, 25);
		Assignment assignment3 = new Assignment(emp2, prj1, 35);
		
		assignmentDao.addAssignment(assignment1);
		assignmentDao.addAssignment(assignment2);
		assignmentDao.addAssignment(assignment3);
		
		Assignment assTemp1 = assignmentDao.getAssignment("EMP1", "PRJ01");
		Assignment assTemp2 = assignmentDao.getAssignment("EMP10", "PRJ01");
		System.out.println(assTemp1);
		System.out.println(assTemp2); //Not found
		
		List<Employee> employees = employeeDao.getEmployees();
		employees.forEach(emp -> System.out.println(emp));
		
		projectDao.getProjects().forEach(prj -> System.out.println(prj));
		
		assignmentDao.getAssignments().forEach(ass -> System.out.println(ass));
		
	}
}
