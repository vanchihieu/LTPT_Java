package app;

import java.util.List;

import dao.CourseDao;
import entity.Course;

public class GetCoursesTask {

	public static void main(String[] args) {
		CourseDao courseDao = new CourseDao();
		List<Course> list = courseDao.getCourses("Database System Concepts");
		for (Course course : list) {
			System.out.println(course);
		}
	}

}
