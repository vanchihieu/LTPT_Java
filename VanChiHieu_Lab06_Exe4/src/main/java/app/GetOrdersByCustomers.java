package app;

import dao.OrderDao;

public class GetOrdersByCustomers {

	public static void main(String[] args) {
		OrderDao orderDao = new OrderDao();
		orderDao.getOrdersByCustomers().entrySet().forEach(m -> System.out.println(m));
	}

}
