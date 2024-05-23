package services;

import dao.BookDAO;
import entities.Book;
import jakarta.persistence.EntityManager;

import java.util.List;

public class BookService implements BookDAO {

    private EntityManager entityManager;

    public BookService(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
//     Liệt kê danh sách các cuốn sách được viết bởi tác giả nào đó khi biết tên tác giả và
//có điểm đánh giá từ mấy sao trở lên.
    // select b from books b join reviews r
    // on b.ISBN = reviews.ISBN
    // where b.name = name and r.rating >= rating
    @Override
    public List<Book> listRatedBooks(String author, int rating) {
//        String query = "select b.* from books b join reviews r on b.ISBN = r.ISBN where b.name = :author and r.rating >= :rating";
//        return entityManager.createQuery(query, Book.class)
//                .setParameter("author", author)
//                .setParameter("rating", rating)
//                .getResultList();

        return entityManager.createQuery("select b from Book b join b.reviews r join b.authors a where a = :author and r.rating >= :rating", Book.class)
                .setParameter("author", author)
                .setParameter("rating", rating)
                .getResultList();
    }
}
