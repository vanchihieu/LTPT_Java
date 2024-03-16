package app;

import dao.ProductDao;

public class DeleteProductsByCategory {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ProductDao productDao = new ProductDao();

		// Xóa nhiều document theo category
		System.out.println(productDao.deleteProductsByCategory("TV"));
	}
}
