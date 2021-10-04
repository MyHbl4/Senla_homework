package task4.datasource.impl;

import java.util.ArrayList;
import java.util.List;
import task4.datasource.BookDataSource;
import task4.model.Book;

public class BookDataSourceImpl implements BookDataSource {
  private final List<Book> books = new ArrayList<>();

  public BookDataSourceImpl() {
    initData();
  }

  public void initData() {}

  @Override
  public List<Book> getBooks() {
    return books;
  }

  @Override
  public Book findBookById(int id) {
    Book book1 = null;
    for (Book book : getBooks()) {
      if (book.getId() == id) {
        book1 = book;
      }
    }
    return book1;
  }
}
