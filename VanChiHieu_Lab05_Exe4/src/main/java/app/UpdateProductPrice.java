package app;

import dao.ProductDao;

public class UpdateProductPrice {
	public static void main(String[] args) {
		ProductDao productDao = new ProductDao();
		
		boolean result = productDao.updateProductPrice(1000, 200);
		
		if (result) {
			System.out.println("Update successfully!");
		} else {
			System.out.println("Update failed!");
		}
	}
}
