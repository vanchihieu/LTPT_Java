package app;

import services.BookService;
import services.EntityManagerFactoryUtil;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactoryUtil entityManagerFactoryUtil = new EntityManagerFactoryUtil();

        BookService bookService = new BookService(entityManagerFactoryUtil.getEnManager());
        System.out.println(bookService.listRatedBooks("Javascript: A Handbook of Agile Software Craftsmanship", 2));
    }
}
