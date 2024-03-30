package app;

import dao.StaffDao;
import entity.Phone;
import entity.Staff;

public class InsertStaffTask {

	public static void main(String[] args) {
		StaffDao staffDao = new StaffDao();
//		Staff s = new Staff(989l,"Nguyen","Bac",new Phone("company", "214124124"),"21@gmail",
//				new Staff(1l));
//		boolean insertStaff = staffDao.insertStaff(s);
//		System.out.println(insertStaff);
		System.out.println(staffDao.findById(989l));
	}

}
