package app;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import dao.CustomerDao;
import entity.Address;
import entity.Customer;
import entity.Phone;

public class InsertOneDocTask {

	public static void main(String[] args) {
//		ProductDao productDao = new ProductDao();
//		Product p = new Product(999l,"Quan Bo","Ao hoa","Dior",List.of("red","blue"), 2023, 2155.6215);
//		boolean insertDoc = productDao.insertDoc(p);
//		System.out.println(insertDoc);
		CustomerDao customerDao =  new CustomerDao();
		Customer customer = new Customer("HA123","Hoang","Nhan Nghia", "nghia@gmail.com", 
				new Date(2023-1900,Calendar.MARCH,26),
				List.of(new Phone("home","123"),new Phone("company", "08120412")),
				new Address("HN","TD","23/27 Mai Lao Bang",1234));
		boolean insertCustomer = customerDao.insertCustomer(customer);
		System.out.println(insertCustomer);
	}

}
