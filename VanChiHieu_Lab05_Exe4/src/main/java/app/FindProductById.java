package app;

import dao.ProductDao;
import entities.Product;

public class FindProductById {
	public static void main(String[] args) {
		ProductDao productDao = new ProductDao();
		Product product = productDao.findProductById(1000);
		System.out.println(product);
	}
}
