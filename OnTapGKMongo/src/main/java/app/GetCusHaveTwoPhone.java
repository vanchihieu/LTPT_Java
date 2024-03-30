package app;

import dao.CustomerDao;

public class GetCusHaveTwoPhone {

	public static void main(String[] args) {
		CustomerDao customerDao = new CustomerDao();
		customerDao.getCustomerPhoneGtTwo().forEach(p -> System.out.println(p));
	}

}
