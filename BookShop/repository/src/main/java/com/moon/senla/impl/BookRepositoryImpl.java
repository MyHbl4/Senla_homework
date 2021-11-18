package com.moon.senla.impl;

import java.time.LocalDate;
import java.util.List;

import com.moon.senla.BookRepository;
import com.moon.senla.annotations.InjectByType;
import com.moon.senla.entity.Book;
import com.moon.senla.enums.Availability;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BookRepositoryImpl implements BookRepository {
  private static final Logger logger = LoggerFactory.getLogger(BookRepositoryImpl.class);
  @InjectByType private BookDao bookDAO;

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
      Book book = bookDAO.read(id);
      book.setAvailability(Availability.OUT_OF_STOCK);
      bookDAO.update(book);
      logger.info(
          "Method completed - " + Thread.currentThread().getStackTrace()[1].getMethodName());
    } catch (Exception e) {
      logger.warn(
          "Failed to execute the method - "
              + Thread.currentThread().getStackTrace()[1].getMethodName(),
          e);
    }
  }

  @Override
  public void removeBooks(List<Book> books) {
    try {
      for (Book book : books) {
        if (bookDAO.read((int) book.getId()) != null) {
          book.setAvailability(Availability.OUT_OF_STOCK);
          bookDAO.update(book);
        }
      }
      logger.info(
          "Method completed - " + Thread.currentThread().getStackTrace()[1].getMethodName());
    } catch (Exception e) {
      logger.warn(
          "Failed to execute the method - "
              + Thread.currentThread().getStackTrace()[1].getMethodName(),
          e);
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
      logger.info(
          "Method completed - " + Thread.currentThread().getStackTrace()[1].getMethodName());
    } catch (Exception e) {
      logger.warn(
          "Failed to execute the method - "
              + Thread.currentThread().getStackTrace()[1].getMethodName(),
          e);
    }
    return availability;
  }

  @Override
  public void restoreBook(Book myBook) {
    try {
      for (Book book : bookDAO.readAll()) {
        if (myBook.getTitle().equals(book.getTitle())
            && book.getAvailability().equals(Availability.OUT_OF_STOCK)) {
          book.setAvailability(Availability.IN_STOCK);
          book.setDeliveryDate(LocalDate.now());
          bookDAO.update(book);
          return;
        }
      }
      logger.info(
          "Method completed - " + Thread.currentThread().getStackTrace()[1].getMethodName());
    } catch (Exception e) {
      logger.warn(
          "Failed to execute the method - "
              + Thread.currentThread().getStackTrace()[1].getMethodName(),
          e);
    }
  }
}
