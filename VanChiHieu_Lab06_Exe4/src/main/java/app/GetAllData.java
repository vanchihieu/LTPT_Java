package app;

import dao.ProductDao;

public class GetAllData {

	public static void main(String[] args) {
		ProductDao productDao = new ProductDao();
		productDao.getAllProduct().forEach(p -> System.out.println(p));
	}

}
