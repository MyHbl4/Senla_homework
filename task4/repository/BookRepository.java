package task4.repository;

import java.util.List;
import task4.model.Book;

public interface BookRepository {

  List<Book> getAll();

  Book findBookById(int id);

//  void checkAvailabilityBooks(List<Book> books);
}
