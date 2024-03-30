package app;

import dao.OrderDao;

public class GetTotalProductTask {

	public static void main(String[] args) {
		OrderDao orderDao = new OrderDao();
		orderDao.getTotalProduct().entrySet().forEach(t -> System.out.println(t));
	}

}
