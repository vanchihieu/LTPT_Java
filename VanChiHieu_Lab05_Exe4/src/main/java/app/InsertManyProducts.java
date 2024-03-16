package app;

import java.util.List;

import dao.ProductDao;
import entities.Product;

public class InsertManyProducts {
	public static void main(String[] args) {
		ProductDao productDao = new ProductDao();
		// Insert many products
		List<Product> products = List.of(
				new Product(1001, "Hieu", "Mountain", "Mountain 100", List.of("Red", "Black"), 2024, 500),
				new Product(1002, "Em", "Mountain", "Mountain 200", List.of("Red", "Black"), 2024, 500),
				new Product(1003, "Tam", "Mountain", "Mountain 300", List.of("Red", "Black"), 2024, 500),
				new Product(1004, "Nhan", "Mountain", "Mountain 400", List.of("Red", "Black"), 2024, 500));
		
		boolean result = productDao.insertManyProducts(products);
		if (result) {
			System.out.println("Insert successfully!");
		} else {
			System.out.println("Insert failed!");
		}
	}
}
