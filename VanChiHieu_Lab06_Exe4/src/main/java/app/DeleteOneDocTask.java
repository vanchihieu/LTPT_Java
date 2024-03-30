package app;

import dao.ProductDao;

public class DeleteOneDocTask {

	public static void main(String[] args) {
		ProductDao productDao = new ProductDao();
		boolean deleteDoc = productDao.deleteDoc(998l);
		System.out.println(deleteDoc);
	}

}
