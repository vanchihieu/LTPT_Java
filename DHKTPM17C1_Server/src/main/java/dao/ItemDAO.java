package dao;

import entities.Item;

import java.util.List;

public interface ItemDAO {
    public List<Item> listItems(String supplierName);
}
