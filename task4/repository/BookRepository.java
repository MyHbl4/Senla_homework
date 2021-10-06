package task4.repository;

import java.util.List;
import task4.model.Book;

public interface BookRepository {

  List<Book> getAll();

  Book findBookById(int id);

  void removeBook(int id);

  void removeBooks(List<Book> books);

  boolean checkBookInBooks(Book myBook);

  void restoreBook(Book myBook);

  //  void checkAvailabilityBooks(List<Book> books);
}
