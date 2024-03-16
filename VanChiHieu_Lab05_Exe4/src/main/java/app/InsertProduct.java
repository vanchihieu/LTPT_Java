package app;

import java.util.Arrays;

import dao.ProductDao;
import entities.Product;

public class InsertProduct {
	public static void main(String[] args) {
		ProductDao productDao = new ProductDao();
		// Insert a new product
		Product product = new Product(1005, "Hieu", "Mountain", "Mountain 100", Arrays.asList("Red", "Black"), 2024, 500);
		boolean result = productDao.insertProduct(product);
		if (result) {
			System.out.println("Insert successfully!");
		} else {
			System.out.println("Insert failed!");
		}
	}
}
