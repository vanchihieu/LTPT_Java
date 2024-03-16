package app;

import dao.ProductDao;

public class FindOneAndUpdateProduct {
	public static void main(String[] args) {
		ProductDao productDao = new ProductDao();
		// findOneAndUpdate a product
		boolean result = productDao.findOneAndUpdateProduct("Mountain", 750);
		if (result) {
			System.out.println("Update successfully!");
		} else {
			System.out.println("Update failed!");
		}
	}
}
