package app;

import dao.ProductDao;

public class UpdateManyDocTask {

	public static void main(String[] args) {
		ProductDao productDao = new ProductDao();
		boolean updateManyDoc = productDao.updateManyDoc("Levi");
		System.out.println(updateManyDoc);
	}

}
