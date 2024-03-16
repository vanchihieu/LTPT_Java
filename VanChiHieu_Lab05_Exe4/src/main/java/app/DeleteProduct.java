package app;

import dao.ProductDao;

public class DeleteProduct {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ProductDao productDao = new ProductDao();
		
		// Xóa một document theo productId
		System.out.println(productDao.deleteProduct(1005));
	}
}
