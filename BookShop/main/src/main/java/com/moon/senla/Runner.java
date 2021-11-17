package com.moon.senla;

import java.util.ArrayList;
import java.util.List;

import com.moon.senla.entity.Book;
import com.moon.senla.entity.Order;
import com.moon.senla.impl.BookDao;
import com.moon.senla.impl.OrderBooksDao;
import com.moon.senla.impl.OrderDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Runner {
  private static final Logger logger = LoggerFactory.getLogger(Runner.class);

  public static void main(String[] args) {
    OrderDao order = new OrderDao();
    BookDao book = new BookDao();
    OrderBooksDao orderBooks = new OrderBooksDao();
        List<Book> books = new ArrayList<>();
        books.add(book.read(1));
        books.add(book.read(3));
        System.out.println(books);
    book.create(new Book("Romeo", "Gottfried", 200, 1918));
    order.create(new Order("zakria"));
//    orderBooks.create(new OrderBooks(1, 1));
//    orderBooks.create(new OrderBooks(2, 2));
    order.create(new Order("tester", books));



//    BookDao book = new BookDao();
//    List<Book> books = book.readAll();
//    for(Book b:books){
//      System.out.println(b.getTitle());
//    }

    //    new LoggerProperty();
    //    MenuController menuController =
    // ObjectFactory.getInstance().createObject(MenuController.class);
    //    menuController.run();
    //    logger.info("The program is closed");

//    CreateDB create = new CreateDB();
//    create.create();

//    Transaction transaction = null;
//    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
//      Book book =
//          new Book(
//              "Romeo and Juliet5",
//              "Gottfried Whimper",
//              174,
//              1918);
//      transaction = session.beginTransaction();
//      session.save(book);
//      transaction.commit();
//    } catch (Exception e) {
//      if (transaction != null) {
//        transaction.rollback();
//      }
//      e.printStackTrace();
//    }

//        BookDao book = new BookDao();
//    //    book.create(new Book("test", "author", 200, 2000));
//    book.delete(book.read(9));
//    List<Book> books = book.readAll();
//    for(Book b:books){
//      System.out.println(b);
//    }
//    System.out.println(book.read(1));

//    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
//      Order order =
//              new Order("vasya"
//                      );
//      transaction = session.beginTransaction();
//      session.save(order);
//      transaction.commit();
//    } catch (Exception e) {
//      if (transaction != null) {
//        transaction.rollback();
//      }
//      e.printStackTrace();
//    }
  }
}
