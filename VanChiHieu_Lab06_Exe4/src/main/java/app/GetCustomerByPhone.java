package app;

import dao.CustomerDao;
import entity.Customer;

public class GetCustomerByPhone {

	public static void main(String[] args) {
		CustomerDao customerDao = new CustomerDao();
		Customer customerByPhone = customerDao.findCustomerByPhone("(212) 945-8823");
		System.out.println(customerByPhone);
	}

}
