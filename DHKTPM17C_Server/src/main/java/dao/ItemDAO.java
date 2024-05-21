package dao;

import entities.Item;

import java.util.List;

public interface ItemDAO {
    List<Item> listItems (String supplierName);
}
