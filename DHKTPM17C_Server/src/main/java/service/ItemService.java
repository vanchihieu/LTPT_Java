package service;

import dao.ItemDAO;
import entities.Food;
import entities.Item;
import entities.Type;
import jakarta.persistence.EntityManager;

import java.util.ArrayList;
import java.util.List;

public class ItemService implements ItemDAO {
    private EntityManager entityManager;

    public ItemService(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    //    Liệt kê danh sách mặt hàng là món đặt biệt của nhà hàng mà có sử dụng nguyên liệu được nhập từ nhà cung cấp nào đó khi biết tên nhà cung cấp (tìm tương đối, không phân biệt chữ thường hoa)
    // select i.* from items i
    // join items_ingredients ii on i.id = ii.item_id
    // join ingredients ig on ii.ingredient_id = ig.ingredient_id
    // where lower(ig.supplier_name) like lower('%supplierName%') and i.on_special = 1
    @Override
    public List<Item> listItems(String supplierName) {
//       String query = "select i.* from items i\n" +
//               "join items_ingredients ii on i.id = ii.item_id\n" +
//               "join ingredients ig on ii.ingredient_id = ig.ingredient_id\n" +
//               "where lower(ig.supplier_name) like lower(:supplierName) and i.on_special = 1";
//        List<Item> items = entityManager.createNativeQuery(query, Item.class)
//                .setParameter("supplierName", "%" + supplierName + "%")
//                .getResultList();
//        return items;

        String query= "select i from Item i join Ingredient ig on i.id = ig.id where lower(ig.supplierName) like lower(:supplierName) and i.onSpecial = true ";
        return entityManager.createQuery(query, Item.class)
                .setParameter("supplierName", "%" + supplierName + "%")
                .getResultList();

//        String supplierNameLower = supplierName.toLowerCase();
//        String jpql = "SELECT i FROM Item i INNER JOIN i.ingredient ig "
//                + "WHERE ig.supplierName = :supplierName AND i.onSpecial = true";
//        return entityManager.createQuery(jpql, Item.class)
//                .setParameter("supplierName", supplierNameLower)
//                .getResultList();
    }
}
