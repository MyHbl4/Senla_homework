package com.moon.senla.impl;

import com.moon.senla.enums.Availability;
import com.moon.senla.Book;
import com.moon.senla.BookDAO;
import com.moon.senla.BookRepository;
import com.moon.senla.annotations.InjectByType;
import java.util.List;

public class BookRepositoryImpl implements BookRepository {
  @InjectByType private BookDAO bookDAO;

  @Override
  public List<Book> getAll() {
    return bookDAO.readAll();
  }

  @Override
  public Book findBookById(int id) {
    return bookDAO.read(id);
  }

  @Override
  public void removeBook(int id) {
    bookDAO.update(id);
  }

  @Override
  public void removeBooks(List<Book> books) {
    for (Book book : books) {
      if (bookDAO.read(Math.toIntExact(book.getId())) != null)
        bookDAO.update(Math.toIntExact(book.getId()));
    }
  }

  @Override
  public boolean checkBookInBooks(Book myBook) {
    boolean availability = true;
    for (Book book : bookDAO.readAll()) {
      if (myBook.getTitle().equals(book.getTitle())
          && book.getAvailability().equals(Availability.OUT_OF_STOCK)) {
        availability = false;
      }
    }
    return availability;
  }

  @Override
  public void restoreBook(Book myBook) {
    for (Book book : bookDAO.readAll()) {
      if (myBook.getTitle().equals(book.getTitle())
          && book.getAvailability().equals(Availability.OUT_OF_STOCK)) {
        bookDAO.updateInAndDate(Math.toIntExact(book.getId()));
        return;
      }
    }
  }
}
