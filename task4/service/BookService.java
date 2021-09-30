package task4.service;

import java.util.List;
import task4.model.Book;

public interface BookService {

  Book findBookById(int id);

  void addBook(Book book);

  void removeBook(int id);

  List<Book> getAll();

  List<Book> getOldBooks();

  void updateBookCsv();

  void downloadBookCsv();

}
