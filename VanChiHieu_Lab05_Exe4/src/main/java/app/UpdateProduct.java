package app;

import java.util.Arrays;

import dao.ProductDao;
import entities.Product;

public class UpdateProduct {
	public static void main(String[] args) {
		ProductDao productDao = new ProductDao();

		// Update a product
		Product product = new Product(1000, "Hieu", "Mountain", "Mountain 101", Arrays.asList("Red", "Black"), 2024, 600);
		boolean result = productDao.updateProduct(product);
		if (result) {
			System.out.println("Update successfully!");
		} else {
			System.out.println("Update failed!");
		}
	}
}
