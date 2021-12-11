package com.moon.senla.repository.impl;

import com.moon.senla.dao.BookDao;
import com.moon.senla.entity.Book;
import com.moon.senla.enums.Availability;
import com.moon.senla.repository.BookRepository;
import java.time.LocalDate;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BookRepositoryImpl implements BookRepository {
    private static final Logger LOGGER = LoggerFactory.getLogger(BookRepositoryImpl.class);
    private BookDao bookDAO;

    @Autowired
    public BookRepositoryImpl(BookDao bookDAO) {
        this.bookDAO = bookDAO;
    }

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
            LOGGER.info(
                "Method completed - " + Thread.currentThread().getStackTrace()[1].getMethodName());
        } catch (Exception e) {
            LOGGER.warn(
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
            LOGGER.info(
                "Method completed - " + Thread.currentThread().getStackTrace()[1].getMethodName());
        } catch (Exception e) {
            LOGGER.warn(
                "Failed to execute the method - "
                    + Thread.currentThread().getStackTrace()[1].getMethodName(),
                e);
        }
    }

    @Override
    public boolean checkBookInBooks(Book myBook) {
        try {
            for (Book book : bookDAO.readAll()) {
                if (myBook.getTitle().equals(book.getTitle())
                    && book.getAvailability().equals(Availability.OUT_OF_STOCK)) {
                    return false;
                }
            }
            LOGGER.info(
                "Method completed - " + Thread.currentThread().getStackTrace()[1].getMethodName());
        } catch (Exception e) {
            LOGGER.warn(
                "Failed to execute the method - "
                    + Thread.currentThread().getStackTrace()[1].getMethodName(),
                e);
        }
        return true;
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
            LOGGER.info(
                "Method completed - " + Thread.currentThread().getStackTrace()[1].getMethodName());
        } catch (Exception e) {
            LOGGER.warn(
                "Failed to execute the method - "
                    + Thread.currentThread().getStackTrace()[1].getMethodName(),
                e);
        }
    }
}
