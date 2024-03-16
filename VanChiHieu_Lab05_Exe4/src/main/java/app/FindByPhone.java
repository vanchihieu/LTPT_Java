package app;

import dao.CustomerDao;
import entities.Customer;

public class FindByPhone {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CustomerDao customerDao = new CustomerDao();
		Customer customer = customerDao.findByPhone("(212) 945-8823");
		System.out.println(customer);
		
	}
}
