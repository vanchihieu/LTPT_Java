package app;

import dao.CustomerDao;

public class GetNumberCustomerByStateTask {

	public static void main(String[] args) {
		CustomerDao customerDao = new CustomerDao();
		customerDao.getNumberCustomerByState().entrySet().forEach(x -> System.out.println(x));
	}

}
