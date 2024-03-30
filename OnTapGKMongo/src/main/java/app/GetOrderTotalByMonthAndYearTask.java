package app;

import dao.OrderDao;

public class GetOrderTotalByMonthAndYearTask {

	public static void main(String[] args) {
		OrderDao orderDao = new OrderDao();
		orderDao.getOrderTotalByMonthAndYear().entrySet().forEach(x -> System.out.println(x));
	}

}
