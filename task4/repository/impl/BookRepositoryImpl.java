package task4.repository.impl;

import java.util.List;
import task4.datasource.BookDataSource;
import task4.enums.Availability;
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
    return bookDataSource.findBookById(id);
  }

  @Override
  public void removeBook(int id) {
    findBookById(id).setAvailability(Availability.OUT_OF_STOCK);
  }

  @Override
  public void removeBooks(List<Book> books) {
    for(Book book:books){
      findBookById((int)book.getId()).setAvailability(Availability.OUT_OF_STOCK);
    }
  }
}
