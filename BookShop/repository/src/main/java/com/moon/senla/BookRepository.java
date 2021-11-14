package com.moon.senla;

import java.util.List;

import com.moon.senla.entity.Book;

public interface BookRepository {

  List<Book> getAll();

  Book findBookById(int id);

  void removeBook(int id);

  void removeBooks(List<Book> books);

  boolean checkBookInBooks(Book myBook);

  void restoreBook(Book myBook);
}
