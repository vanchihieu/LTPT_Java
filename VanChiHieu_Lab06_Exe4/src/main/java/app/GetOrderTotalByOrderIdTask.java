package app;

import org.bson.types.ObjectId;

import dao.OrderDao;

public class GetOrderTotalByOrderIdTask {

	public static void main(String[] args) {
		OrderDao orderDao = new OrderDao();
		double totalCusByOrderId = orderDao.getTotalCusByOrderId(new ObjectId("615279c4dc90aa2be71fd8f9"));
		System.out.println(totalCusByOrderId);
	}

}
