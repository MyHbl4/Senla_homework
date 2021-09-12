package task4.datasource;

import task4.model.Book;

public interface BookRepository {

  void addBook(Book book);

  void inStock(int id);

  void outOfStock(int id);

  void removeBook(int id);

  Book findBookById(int id);

  void printBookRepository();
}
