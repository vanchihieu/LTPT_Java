package app;

import dao.CustomerDao;

public class DeleteCusNotOrderTask {

	public static void main(String[] args) {
		CustomerDao customerDao = new CustomerDao();
		boolean deleteCusNotOrder = customerDao.deleteCusNotOrder();
		System.out.println(deleteCusNotOrder);
	}

}
