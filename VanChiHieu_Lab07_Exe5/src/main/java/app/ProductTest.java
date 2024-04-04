package app;

import dao.ProductDAO;
import entities.Category;
import entities.Product;

import java.util.List;

public class ProductTest {
    public static void main(String[] args) {
        ProductDAO productDAO = new ProductDAO();

//        Product find = productDAO.findProductById(1);
//        System.out.println(find);

//        List<Product> findAll = productDAO.getAllProducts();
//        findAll.forEach(System.out::println);

//        use criteria api
//        Product findId = productDAO.findProductByIdCriteria(1);
//        System.out.println(findId);

//        List<Product> findProductMaxPrice = productDAO.findProductsWithMaxPrice();
//        findProductMaxPrice.forEach(System.out::println);

//        List<Product> findByProductMaxPrice2 = productDAO.findProductsWithMaxPrice2();
//        findByProductMaxPrice2.forEach(System.out::println);

        List<Product> findProductNotSold = productDAO.findProductNotSold();
        findProductNotSold.forEach(System.out::println);
    }
}
