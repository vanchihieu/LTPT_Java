package app;

import java.util.List;

import dao.ProductDao;
import entities.Product;

public class SearchProductByName {
	public static void main(String[] args) {
		ProductDao productDao = new ProductDao();
		List<Product> products = productDao.searchProductByName("Trek Checkpoint SL 5 Women's - 2019");
		for (Product product : products) {
			System.out.println(product);
		}
	}
}
