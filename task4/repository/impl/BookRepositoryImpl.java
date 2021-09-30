package task4.repository.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import task4.datasource.BookDataSource;
import task4.model.Book;
import task4.repository.BookRepository;

public class BookRepositoryImpl implements BookRepository {

  private final BookDataSource bookDataSource;

  public BookRepositoryImpl(BookDataSource bookDataSource) {

    this.bookDataSource = bookDataSource;
  }

  @Override
  public List<Book> getAll() {
    return bookDataSource.getBooks();
  }

  @Override
  public Book findBookById(int id) {
    Book book1 = null;
    for (Book book : bookDataSource.getBooks()) {
      if (book.getId() == id) {
        book1 = book;
      }
    }
    return book1;
  }
}
