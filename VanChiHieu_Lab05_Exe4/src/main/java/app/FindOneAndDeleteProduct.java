package app;

import dao.ProductDao;

public class FindOneAndDeleteProduct {
	public static void main(String[] args) {
		ProductDao productDao = new ProductDao();
		
		// Find a product by its id and delete it
		System.out.println(productDao.findOneAndDeleteProduct("TV"));
	}
}
