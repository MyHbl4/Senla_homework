package task4.repository;

import java.util.List;
import task4.model.Availability;
import task4.model.Book;

public interface BookRepository {

  List<Book> getAll();

  Book findBookById(int id);

  void removeBook(int id);

  void removeBooks(List<Book> books);

//  void checkAvailabilityBooks(List<Book> books);
}
