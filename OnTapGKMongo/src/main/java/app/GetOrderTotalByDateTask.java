package app;

import java.time.Instant;
import java.util.Date;

import dao.OrderDao;

public class GetOrderTotalByDateTask {

	public static void main(String[] args) {
		OrderDao orderDao = new OrderDao();
		Instant desiredDate = Instant.parse("2021-09-28T02:11:23.330Z");
		Date date = Date.from(desiredDate);
		double totalByDate = orderDao.getOrderTotalByDate(date);
		System.out.println(totalByDate);
		
		double total = orderDao.getOrderTotalByDate(desiredDate);
		System.out.println(total);
		
		
	}

}
