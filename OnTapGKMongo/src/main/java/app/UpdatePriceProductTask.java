package app;

import dao.ProductDao;

public class UpdatePriceProductTask {

	public static void main(String[] args) {
		ProductDao productDao = new ProductDao();
		boolean updatePrice = productDao.updatePrice(1l,525.500);
		System.out.println(updatePrice);
	}

}
