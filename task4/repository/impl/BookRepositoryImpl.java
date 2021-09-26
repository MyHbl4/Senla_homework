package task4.repository.impl;

import java.time.LocalDate;
import task4.datasource.BookDataSource;
import task4.model.Book;
import task4.repository.BookRepository;
import task4.util.BookArrayList;

public class BookRepositoryImpl implements BookRepository {

  private final BookDataSource bookDataSource;

  public BookRepositoryImpl(BookDataSource bookDataSource) {

    this.bookDataSource = bookDataSource;
  }

  @Override
  public BookArrayList getAll() {
    return bookDataSource.getBooks();
  }

  @Override
  public Book findBookById(int id) {
    Book book = null;
    for (int i = 0; i < bookDataSource.getBooks().size(); i++) {
      if (bookDataSource.getBooks().get(i).getId() == id) {
        book = bookDataSource.getBooks().get(i);
      }
    }
    return book;
  }

  @Override
  public Book findBookByTitle(String title) {
    Book book = null;
    for (int i = 0; i < bookDataSource.getBooks().size(); i++) {
      if (bookDataSource.getBooks().get(i).getTitle().equals(title)) {
        book = bookDataSource.getBooks().get(i);
      }
    }
    return book;
  }

  @Override
  public Book[] getArrayBook() {
    Book[] books = new Book[bookDataSource.getBooks().size()];
    for (int i = 0; i < bookDataSource.getBooks().size(); i++) {
      books[i] = bookDataSource.getBooks().get(i);
    }
    return books;
  }

  @Override
  public int getCountOldBook() {
    int count = 0;
    for (int i = 0; i < bookDataSource.getBooks().size(); i++) {
      if (bookDataSource
          .getBooks()
          .get(i)
          .getDeliveryDate()
          .isBefore(LocalDate.now().minusMonths(6))) {
        count++;
      }
    }
    return count;
  }

  @Override
  public Book[] getArrayOldBook() {
    Book[] oldBooks = new Book[getCountOldBook()];
    int indexOldBooks = 0;
    for (int i = 0; i < bookDataSource.getBooks().size(); i++) {
      if (bookDataSource
          .getBooks()
          .get(i)
          .getDeliveryDate()
          .isBefore(LocalDate.now().minusMonths(6))) {
        oldBooks[indexOldBooks] = bookDataSource.getBooks().get(i);
        indexOldBooks++;
      }
    }
    return oldBooks;
  }
}
