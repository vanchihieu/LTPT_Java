package demo;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

import dao.AbstractDao;
import dao.CustomerDao;
import dao.DaoUtil;
import dao.JDBCUtil;
import dao.ProductDao;
import entity.Customer;
import entity.Phone;

public class demo {
	public static void main(String[] args) throws SQLException {
		MongoClient client = MongoClients.create();
		AbstractDao abstractDao = new AbstractDao(client);
		CustomerDao customerDao = new CustomerDao(client);
		ProductDao productDao = new ProductDao(client);
		DaoUtil daoUtil = new DaoUtil(client);
		
//		JDBCUtil jdbc = new JDBCUtil();
		
//		List<Product> products = JDBCUtil.getProductsJDBC();
		
		Map<Integer, Integer> map = daoUtil.getTotalByYear();
		map.entrySet().iterator().forEachRemaining(entry ->{
			System.out.println("model year: "+ entry.getKey());
			System.out.println("num of product: "+ entry.getValue());
		});
		
//		int n = productDao.addProducts(products);
//		System.out.println(n);
		
		
//		List<Customer> customers = jdbc.getCustomersJDBC();
//		customers.forEach(cus -> System.out.println(cus));
		
		
//		Customer cus = customerDao.getCustomer("423");
//		System.out.println(cus);
		
//		customerDao.addCustomers(customers);
		
//		Customer customer2 = new Customer("723", "john Smith", new Date(198 - 1900, Calendar.JULY, 12));
//		Customer customer3 = new Customer("423", "Anna Tran", new Date(2023 - 1900, Calendar.MARCH, 12));

//		customer.setPhones(Arrays.asList(new Phone("Home", "032423422")));;
		
		
//		int n = customerDao.addCustomers(Arrays.asList(customer2, customer3));
//		System.out.println(n);
		
//		abstractDao.getDatabase().listCollectionNames()
//		.forEach(colName -> System.out.println(colName));
		
//		abstractDao.getClient().listDatabaseNames()
//		.forEach(dbName ->{
//			System.out.println(dbName);
//		});
	}
}
