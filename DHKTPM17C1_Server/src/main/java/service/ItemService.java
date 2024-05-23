package service;

import dao.ItemDAO;
import entities.Item;
import jakarta.persistence.EntityManager;

import java.util.List;

public class ItemService implements ItemDAO {
    private EntityManager entityManager;

    public ItemService(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Item> listItems(String supplierName) {
        String jpql = "select  i from Item i join i.ingredients  ig where lower(ig.supplierName) like lower(:supplierName) and i.onSpecial = true";
        List<Item> items = entityManager.createQuery(jpql, Item.class)
                .setParameter("supplierName", "%" + supplierName + "%")
                .getResultList();
        return items;
    }
}
