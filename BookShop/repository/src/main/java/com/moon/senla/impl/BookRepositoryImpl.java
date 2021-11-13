package com.moon.senla.impl;

import com.moon.senla.enums.Availability;
import com.moon.senla.Book;
import com.moon.senla.BookDAO;
import com.moon.senla.BookRepository;
import com.moon.senla.annotations.InjectByType;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BookRepositoryImpl implements BookRepository {
  @InjectByType private BookDAO bookDAO;
  private static final Logger logger = LoggerFactory.getLogger(BookRepositoryImpl.class);

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
    try {
      bookDAO.update(id);
      logger.info("Method completed - " + Thread.currentThread().getStackTrace()[1].getMethodName());
    } catch (Exception e) {
      logger.warn("Failed to execute the method - " + Thread.currentThread().getStackTrace()[1].getMethodName(), e);
    }
  }

  @Override
  public void removeBooks(List<Book> books) {
    try {
      for (Book book : books) {
        if (bookDAO.read(Math.toIntExact(book.getId())) != null)
          bookDAO.update(Math.toIntExact(book.getId()));
      }
      logger.info("Method completed - " + Thread.currentThread().getStackTrace()[1].getMethodName());
    } catch (Exception e) {
      logger.warn("Failed to execute the method - " + Thread.currentThread().getStackTrace()[1].getMethodName(), e);
    }
  }

  @Override
  public boolean checkBookInBooks(Book myBook) {
    boolean availability = false;
    try {
      availability = true;
      for (Book book : bookDAO.readAll()) {
        if (myBook.getTitle().equals(book.getTitle())
            && book.getAvailability().equals(Availability.OUT_OF_STOCK)) {
          availability = false;
        }
      }
      logger.info("Method completed - " + Thread.currentThread().getStackTrace()[1].getMethodName());
    } catch (Exception e) {
      logger.warn("Failed to execute the method - " + Thread.currentThread().getStackTrace()[1].getMethodName(), e);
    }
    return availability;
  }

  @Override
  public void restoreBook(Book myBook) {
    try {
      for (Book book : bookDAO.readAll()) {
        if (myBook.getTitle().equals(book.getTitle())
            && book.getAvailability().equals(Availability.OUT_OF_STOCK)) {
          bookDAO.updateInAndDate(Math.toIntExact(book.getId()));
          return;
        }
      }
      logger.info("Method completed - " + Thread.currentThread().getStackTrace()[1].getMethodName());
    } catch (Exception e) {
      logger.warn("Failed to execute the method - " + Thread.currentThread().getStackTrace()[1].getMethodName(), e);
    }
  }
}
