package app;

import java.util.List;

import dao.ProductDao;
import entity.Product;

public class FindByTextIndexTask {

	public static void main(String[] args) {
		ProductDao productDao = new ProductDao();
		List<Product> products = productDao.findByTextIndex("Ritchey Timberwolf Frameset");
		products.forEach(x -> System.out.println(x));
		
	}

}
