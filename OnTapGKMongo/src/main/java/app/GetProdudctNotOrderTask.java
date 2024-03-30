package app;

import dao.ProductDao;

public class GetProdudctNotOrderTask {

	public static void main(String[] args) {
		ProductDao productDao = new ProductDao();
		productDao.getProudctNotOrder().forEach(x -> System.out.println(x));;
	}

}
