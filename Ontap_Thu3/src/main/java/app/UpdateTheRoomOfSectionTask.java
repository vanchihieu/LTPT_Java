package app;

import dao.CourseDao;

public class UpdateTheRoomOfSectionTask {
	public static void main(String[] args) {
		CourseDao courseDao = new CourseDao();
		boolean rs = courseDao.updateTheRoomOfSection("CSC101", "CSC101-01", "bac");
		System.out.println(rs);
	}
}
