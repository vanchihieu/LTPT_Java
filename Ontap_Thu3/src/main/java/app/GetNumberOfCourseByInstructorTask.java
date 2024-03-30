package app;

import dao.CourseDao;

public class GetNumberOfCourseByInstructorTask {
	public static void main(String[] args) {
		CourseDao courseDao = new CourseDao();
		courseDao.getNumberOfCourseByInstructor().entrySet().forEach(x -> System.out.println(x));
	}
}
