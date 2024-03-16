package app;

import java.util.Map;

import dao.CustomerDao;

public class GetNumberCustomerByState {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CustomerDao customerDao = new CustomerDao();
		Map<String, Integer> map = customerDao.getNumberCustomerByState();
		for (Map.Entry<String, Integer> entry : map.entrySet()) {
			System.out.println(entry.getKey() + " - " + entry.getValue());
		}
	}
}
