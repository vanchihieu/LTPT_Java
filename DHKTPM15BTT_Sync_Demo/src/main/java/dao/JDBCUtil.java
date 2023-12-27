package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;

import entity.Customer;
import entity.Phone;

public class JDBCUtil {
	private Connection con;
	
	public JDBCUtil() {
		String url = "jdbc:sqlserver://localhost:1433;databaseName=Bikestores";
		
		try {
			con = DriverManager.getConnection(url, "sa", "sapassowrd");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public List<Customer> getCustomersJDBC() throws SQLException {
		List<Customer> customers = new ArrayList<Customer>();
		Random rd = new Random();

		

		String sql = "select * from [sales].[customers]";
		PreparedStatement stmt = con.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {
			Date date = new Date((rd.nextInt(6) + 1995) - 1900, rd.nextInt(12), rd.nextInt(28) + 1);
			Customer customer = new Customer(rs.getString("customer_id"), rs.getString("first_name"), date);
			String number = rs.getString("phone");
			if (number != null) {
				Phone phone = new Phone(number, "Home");
				customer.setPhones(Arrays.asList(phone));
			}

			customers.add(customer);
		}
		return customers;
	}
}
