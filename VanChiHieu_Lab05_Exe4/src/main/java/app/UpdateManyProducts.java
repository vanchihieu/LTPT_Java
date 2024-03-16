package app;

import dao.ProductDao;

public class UpdateManyProducts {
	public static void main(String[] args) {
		ProductDao productDao = new ProductDao();
		
		// Update many products
		boolean result = productDao.updateManyProducts("Mountain", 650);
		if (result) {
			System.out.println("Update successfully!");
		} else {
			System.out.println("Update failed!");
		}
	}
}
