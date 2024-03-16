package app;

import java.util.List;

import dao.ProductDao;
import entities.Product;

public class FindProductsWithMaxPrice {
	public static void main(String[] args) {
		ProductDao productDao = new ProductDao();
		
		List<Product> product = productDao.findProductsWithMaxPrice();
		for (Product p : product) {
			System.out.println(p);
		}
	}
}
