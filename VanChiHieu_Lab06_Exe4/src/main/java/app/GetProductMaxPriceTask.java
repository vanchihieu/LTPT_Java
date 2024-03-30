package app;

import dao.ProductDao;

public class GetProductMaxPriceTask {

	public static void main(String[] args) {
		ProductDao productDao = new ProductDao();
		productDao.getProductMaxPrice().forEach(x -> System.out.println(x));
	}

}
