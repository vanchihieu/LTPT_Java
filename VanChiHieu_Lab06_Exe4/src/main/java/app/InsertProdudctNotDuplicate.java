package app;

import java.util.List;

import dao.ProductDao;
import entity.Product;

public class InsertProdudctNotDuplicate {

	public static void main(String[] args) {
		ProductDao productDao = new ProductDao();
		Product p = new Product(5214l,"Quan Mau","Ao Gam","An ca sa",List.of("red","blue"), 2023,12421.412);
		boolean addNotDuplicateProdct = productDao.addNotDuplicateProdct(p);
		System.out.println(addNotDuplicateProdct);
	}

}
