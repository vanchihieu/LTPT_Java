package app;

import java.util.List;

import dao.ProductDao;
import entity.Product;

public class UpdateDocTask {

	public static void main(String[] args) {
		ProductDao productDao = new ProductDao();
		Product p = new Product(997l,"Quan","Ao","Levi",List.of("red","blue"), 2013, 3155.8215);
		boolean updateDoc = productDao.updateDoc(p);
		System.out.println(updateDoc);
	}

}
