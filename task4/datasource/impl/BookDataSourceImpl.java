package task4.datasource.impl;

import java.util.ArrayList;
import java.util.List;
import task4.datasource.BookDataSource;
import task4.model.Book;

public class BookDataSourceImpl implements BookDataSource {
  private final List<Book> books = new ArrayList<>();
  private final List<Book> booksCsv = new ArrayList<>();

  public BookDataSourceImpl() {
    initData();
  }

  public void initData() {}

  @Override
  public List<Book> getBooks() {
    return books;
  }
}
