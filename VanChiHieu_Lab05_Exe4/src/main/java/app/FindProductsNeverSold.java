package app;

import java.util.List;

import dao.ProductDao;
import entities.Product;

public class FindProductsNeverSold {
	public static void main(String[] args) {
		ProductDao productDao = new ProductDao();
		List<Product> products = productDao.findProductsNeverSold();
		for (Product product : products) {
			System.out.println(product);
		}
	}
}
