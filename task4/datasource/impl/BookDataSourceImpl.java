package task4.datasource.impl;

import java.util.ArrayList;
import java.util.List;
import task4.DI.annotations.InjectByType;
import task4.datasource.BookDataSource;
import task4.jdbc.BookDAO;
import task4.model.Book;

public class BookDataSourceImpl implements BookDataSource {
  private final List<Book> books = new ArrayList<>();
  @InjectByType private BookDAO bookDAO;

  public BookDataSourceImpl() {
    initData();
  }

  public void initData() {}

  @Override
  public List<Book> getBooks() {
    return bookDAO.readAll();
  }

  @Override
  public Book findBookById(int id) {
    return bookDAO.read(id);
  }
}
