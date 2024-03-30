package app;

import java.util.List;

import dao.ProductDao;
import entity.Product;

public class InsertMany {

	public static void main(String[] args) {
		ProductDao productDao = new ProductDao();
		List<Product> products = List.of(
				new Product(997l,"Ao","Mau","Channel",List.of("red","blue"), 2013, 3155.8215),
				new Product(998l,"Quan","Hoa","D",List.of("red","blue"), 2022, 1125.6215)
				);
		boolean insertManyDoc = productDao.insertManyDoc(products);
		System.out.println(insertManyDoc);
	}

}
