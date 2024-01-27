package exe3;

import java.util.Arrays;
import java.util.Random;

import exe3.StudentAnalysis.Student;

public class StudentAnalysis {
	public static class Student {
		private String firstName;
		private String lastName;
		private double age;
		private int grade;
		private boolean isCurrent;

		public Student(String firstName, String lastName, double age, int grade, boolean isCurrent) {
			this.firstName = firstName;
			this.lastName = lastName;
			this.age = age;
			this.grade = grade;
			this.isCurrent = isCurrent;
		}

		public String getFirstName() {
			return firstName;
		}

		public String getLastName() {
			return lastName;
		}

		public double getAge() {
			return age;
		}

		public int getGrade() {
			return grade;
		}

		public boolean checkIsCurrent() {
			return isCurrent;
		}
	}

	private static String[] firstNames = { "Thanh", "Thao", "Tuan", "Hong", "Lan", "Phuong" };
	private static String[] lastNames = { "Nguyen", "Le", "Tran", "Mai", "Lam", "Ha" };

	public static Student[] generateStudentData() {
		final int N_STUDENTS = 80_000_000;
		final int N_CURRENT_STUDENTS = 1_000_000;
		Student[] students = new Student[N_STUDENTS];
		Random r = new Random(123);
		for (int i = 0; i < N_STUDENTS; i++) {
			final String firstName = firstNames[r.nextInt(firstNames.length)];
			final String lastName = lastNames[r.nextInt(lastNames.length)];
			final double age = r.nextDouble() * 100.0;
			final int grade = 1 + r.nextInt(100);
			final boolean current = (i < N_CURRENT_STUDENTS);

			students[i] = new Student(firstName, lastName, age, grade, current);
		}
		return students;
	}

	public static void main(String[] args) {
		// Generate student data
		Student[] students = generateStudentData();

		// Compute the average age using loops
		long startTime = System.currentTimeMillis();
		double totalAge = 0;
		int activeStudentCount = 0;
		for (Student student : students) {
			if (student.checkIsCurrent()) {
				totalAge += student.getAge();
				activeStudentCount++;
			}
		}
		double averageAge = totalAge / activeStudentCount;
		long endTime = System.currentTimeMillis();
		System.out.println("Average age using loops: " + averageAge);
		System.out.println("Execution time using loops: " + (endTime - startTime) + "ms");

		// Compute the average age using streams
		startTime = System.currentTimeMillis();
		averageAge = Arrays.stream(students).filter(Student::checkIsCurrent).mapToDouble(Student::getAge).average()
				.orElse(0);
		endTime = System.currentTimeMillis();
		System.out.println("Average age using streams: " + averageAge);
		System.out.println("Execution time using streams: " + (endTime - startTime) + "ms");

		// Compute the average age using parallel streams
		startTime = System.currentTimeMillis();
		averageAge = Arrays.stream(students).parallel().filter(Student::checkIsCurrent).mapToDouble(Student::getAge)
				.average().orElse(0);
		endTime = System.currentTimeMillis();
		System.out.println("Average age using parallel streams: " + averageAge);
		System.out.println("Execution time using parallel streams: " + (endTime - startTime) + "ms");

		// Count the number of students who are not currently active using parallel
		// streams
		long inactiveStudentCount = Arrays.stream(students).parallel().filter(student -> !student.checkIsCurrent())
				.count();
		System.out.println("Number of inactive students: " + inactiveStudentCount);

		// Count the number of students whose age is greater than 20 using parallel
		// streams
		long studentsAbove20Count = Arrays.stream(students).parallel().filter(student -> student.getAge() > 20).count();
		System.out.println("Number of students above 20 years old: " + studentsAbove20Count);

		// Count the number of students whose grade is less than 65 using parallel
		// streams
		long studentsBelow65Count = Arrays.stream(students).parallel().filter(student -> student.getGrade() < 65)
				.count();
		System.out.println("Number of students below grade 65: " + studentsBelow65Count);
	}

}