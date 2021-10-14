package task4.repository.impl;

import java.time.LocalDate;
import java.util.List;
import task4.DI.factory.ObjectFactory;
import task4.datasource.BookDataSource;
import task4.enums.Availability;
import task4.model.Book;
import task4.repository.BookRepository;

public class BookRepositoryImpl implements BookRepository {

  private final BookDataSource bookDataSource = ObjectFactory.getInstance().createObject(BookDataSource.class);

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
    for (Book book : books) {
      findBookById((int) book.getId()).setAvailability(Availability.OUT_OF_STOCK);
    }
  }

  @Override
  public boolean checkBookInBooks(Book myBook) {
    boolean availability = true;
    for (Book book : bookDataSource.getBooks()) {
      if (myBook.getTitle().equals(book.getTitle())
          && book.getAvailability().equals(Availability.OUT_OF_STOCK)) {
        availability = false;
      }
    }
    return availability;
  }

  @Override
  public void restoreBook(Book myBook) {
    for (Book book : bookDataSource.getBooks()) {
      if (myBook.getTitle().equals(book.getTitle())
          && book.getAvailability().equals(Availability.OUT_OF_STOCK)) {
        book.setAvailability(Availability.IN_STOCK);
        book.setDeliveryDate(LocalDate.now());
        return;
      }
    }
  }
}
