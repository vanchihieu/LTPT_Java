package dao;

import entities.Book;

import java.util.List;

public interface BookDAO {
    public List<Book> listRatedBooks(String author, int rating);
}
