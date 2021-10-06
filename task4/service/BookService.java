package task4.service;

import java.util.List;
import task4.model.Book;

public interface BookService {

  Book findBookById(int id);

  void addBook(Book book);

  void removeBook(int id);

  boolean checkBookInRequests(Book book);

  boolean checkBookInOrders(Book book);

  void removeBookRequest(Book book);

  List<Book> getAll();

  List<Book> getOldBooks();

  List<Book> sortBookByAvailability();

  List<Book> sortBookByPrice();

  List<Book> sortBookByPublication();

  List<Book> sortBookByTitle();

  List<Book> sortOldBookByDate();

  List<Book> sortOldBookByPrice();

  void updateBookCsv();

  void downloadBookCsv();

  void writeBookBd();

  void readBookBd();
}
