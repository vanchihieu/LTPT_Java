package app;

import dao.ProductDAO;
import entities.Category;
import entities.Product;

import java.util.List;
import java.util.Map;

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

//        List<Product> findProductNotSold = productDAO.findProductNotSold();
//        findProductNotSold.forEach(System.out::println);

//        List<Object[]> sumQuantityOfProductSold = productDAO.sumQuantityOfProductSold();
//        sumQuantityOfProductSold.forEach(objects -> {
//            System.out.println("Product name: " + objects[0]);
//            System.out.println("Total quantity: " + objects[1]);
//        });

        Map<Product, Integer> getTotalProduct = productDAO.getTotalProduct();
        getTotalProduct.forEach((product, integer) -> {
            System.out.println("Product name: " + product.getName());
            System.out.println("Total quantity: " + integer);
        });



    }
}
