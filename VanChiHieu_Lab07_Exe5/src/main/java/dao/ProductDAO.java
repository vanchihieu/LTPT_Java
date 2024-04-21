package dao;

import db.Connection;
import entities.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductDAO {
    private EntityManager em;

    public ProductDAO() {
        em = Connection.getInstance().getEmf().createEntityManager();
    }

    // add product
    public boolean addProduct(Product product) {
        try {
            em.getTransaction().begin();
            em.persist(product);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // delete product
    public boolean deleteProduct(int id) {
        try {
            Product product = em.find(Product.class, id);
            em.getTransaction().begin();
            em.remove(product);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // update product
    public boolean updateProduct(Product product) {
        try {
            em.getTransaction().begin();
            em.merge(product);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // find product by id
    // select * from products where product_id = id
    public Product findProductById(int id) {
        return em.find(Product.class, id);
    }

    // get all products
    public List<Product> getAllProducts() {
        return em.createQuery("SELECT p FROM Product p").getResultList();
    }

    // find by id using criteria api
    // select * from products where product_id = id
    public Product findProductByIdCriteria(int id) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Product> criteria = builder.createQuery(Product.class);

        Root<Product> root = criteria.from(Product.class);
        criteria.select(root).where(builder.equal(root.get("id"), id));

        return em.createQuery(criteria).getSingleResult();
    }

    //	2.Tìm danh sách các sản phẩm có giá lớn nhất
    // select * from products where list_price = (select max(list_price) from products)
    public List<Product> findProductsWithMaxPrice() {
        CriteriaBuilder cb = em.getCriteriaBuilder();

// Subquery: SELECT MAX(listPrice) FROM Product
        CriteriaQuery<Double> subquery = cb.createQuery(Double.class);
        Root<Product> subqueryRoot = subquery.from(Product.class);
        subquery.select(cb.max(subqueryRoot.get("listPrice")));
        TypedQuery<Double> typedSubquery = em.createQuery(subquery);
        Double maxListPrice = typedSubquery.getSingleResult();

// Main query: SELECT * FROM Product WHERE listPrice = maxListPrice
        CriteriaQuery<Product> cq = cb.createQuery(Product.class);
        Root<Product> root = cq.from(Product.class);
        cq.select(root);
        cq.where(cb.equal(root.get("listPrice"), maxListPrice));

        TypedQuery<Product> typedQuery = em.createQuery(cq);
        List<Product> resultList = typedQuery.getResultList();

        return resultList;
    }

    //	2.Tìm danh sách các sản phẩm có giá lớn nhất
    // select * from products where list_price = (select max(list_price) from products)
    public List<Product> findProductsWithMaxPrice2() {
        List<Product> products = new ArrayList<Product>();
        try {
            em.getTransaction().begin();
            Query query = em.createQuery("SELECT p FROM Product p WHERE p.listPrice = (SELECT MAX(p2.listPrice) FROM Product p2)");
            products = query.getResultList();
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return products;
    }

    //	3.Tìm danh sách sản phẩm chưa bán được lần nào
    // select * from products where product_id not in (select product_id from order_items)
    public List<Product> findProductNotSold() {
        List<Product> products = new ArrayList<Product>();
        try {
            em.getTransaction().begin();
            Query query = em.createQuery("SELECT p FROM Product p WHERE p.id NOT IN (SELECT oi.product.id FROM OrderItem oi)");
            products = query.getResultList();
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return products;
    }

    // 7. Tính tổng số lượng của từng sản phẩm đã bán ra.
    // select product_id, sum(quantity) from order_items group by product_id
    public List<Object[]> sumQuantityOfProductSold() {
        List<Object[]> result = new ArrayList<Object[]>();
        try {
            em.getTransaction().begin();
            Query query = em.createQuery("SELECT oi.product.id, SUM(oi.quantity) FROM OrderItem oi GROUP BY oi.product.id");
            result = query.getResultList();
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public Map<Product, Integer> getTotalProduct() {
        Map<Product, Integer> result = new HashMap<Product, Integer>();
        try {
            em.getTransaction().begin();
            Query query = em.createQuery("SELECT oi.product, SUM(oi.quantity) FROM OrderItem oi GROUP BY oi.product.id");
            List<Object[]> resultList = query.getResultList();
            for (Object[] objects : resultList) {
                result.put((Product) objects[0], (Integer) objects[1]);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}