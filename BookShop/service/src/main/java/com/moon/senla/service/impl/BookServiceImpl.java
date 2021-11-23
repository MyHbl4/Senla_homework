package com.moon.senla.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.moon.senla.dao.BookDao;
import com.moon.senla.entity.Book;
import com.moon.senla.entity.Order;
import com.moon.senla.entity.Request;
import com.moon.senla.enums.OrderStatus;
import com.moon.senla.repository.BookRepository;
import com.moon.senla.repository.OrderRepository;
import com.moon.senla.repository.RequestRepository;
import com.moon.senla.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class BookServiceImpl implements BookService {
  private static final Logger logger = LoggerFactory.getLogger(BookServiceImpl.class);
  private BookRepository bookRepository;
  private RequestRepository requestRepository;
  private OrderRepository orderRepository;
  private BookDao bookDAO;

  @Value("${FUNCTION_ORDER}")
  private String FUNCTION_ORDER;

  @Value("${MONTHS_STALE_BOOKS}")
  private String MONTHS_STALE_BOOKS;

  @Autowired
  public BookServiceImpl(
      BookRepository bookRepository,
      RequestRepository requestRepository,
      OrderRepository orderRepository,
      BookDao bookDAO) {
    this.bookRepository = bookRepository;
    this.requestRepository = requestRepository;
    this.orderRepository = orderRepository;
    this.bookDAO = bookDAO;
  }

  @Override
  public Book findBookById(int id) {
    return bookRepository.findBookById(id);
  }

  @Override
  public void addBook(Book book) {
    try {
      if (!bookRepository.checkBookInBooks(book)) {
        bookRepository.restoreBook(book);
      } else {
        bookDAO.create(book);
      }
      if (checkBookInRequests(book)) {
        if (checkBookInOrders(book) && (FUNCTION_ORDER.equals("on"))) {
          checkOrder();
        } else {
          removeBookRequest(book);
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

  private void checkOrder() {
    try {
      for (Order order : orderRepository.getAll()) {
        if (orderRepository.checkBooksInOrder(order)) {
          order.setOrderStatusCompleate();
          bookRepository.removeBooks(order.getBooks());
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
  public void removeBook(int id) {
    bookRepository.removeBook(id);
  }

  @Override
  public boolean checkBookInRequests(Book book) {
    boolean availability = false;
    try {
      for (Request request : requestRepository.getAll()) {
        if (book.getTitle().equals(request.getBook().getTitle()) && request.getCount() > 0) {
          availability = true;
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
  public boolean checkBookInOrders(Book book) {
    boolean availability = false;
    for (Order order : orderRepository.getAll()) {
      if (order.getOrderStatus().equals(OrderStatus.NEW)) {
        for (Book book1 : order.getBooks()) {
          if (book1.getTitle().equals(book.getTitle())) {
            availability = true;
          }
        }
      }
    }
    return availability;
  }

  @Override
  public void removeBookRequest(Book book) {
    try {
      if (checkBookInRequests(book)) {
        for (Request request : requestRepository.getAll()) {
          if (book.getTitle().equals(request.getBook().getTitle()) && request.getCount() > 0) {
            request.setCount(request.getCount() - 1);
            break;
          }
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
  public List<Book> getAll() {
    return bookRepository.getAll();
  }

  @Override
  public List<Book> getOldBooks() {
    List<Book> oldBooks = null;
    try {
      oldBooks = new ArrayList<>();
      for (Book book : bookRepository.getAll()) {
        if (book.getDeliveryDate()
            .isBefore(LocalDate.now().minusMonths(Long.parseLong(MONTHS_STALE_BOOKS)))) {
          oldBooks.add(book);
        }
      }
      logger.info(
          "Method completed - " + Thread.currentThread().getStackTrace()[1].getMethodName());
    } catch (NumberFormatException e) {
      logger.warn(
          "Failed to execute the method - "
              + Thread.currentThread().getStackTrace()[1].getMethodName(),
          e);
    }
    return oldBooks;
  }

  @Override
  public List<Book> sortBookByAvailability() {
    List<Book> sortBooks = null;
    try {
      sortBooks = bookRepository.getAll();
      sortBooks.sort(Comparator.comparing(Book::getAvailability));
      logger.info(
          "Method completed - " + Thread.currentThread().getStackTrace()[1].getMethodName());
    } catch (Exception e) {
      logger.warn(
          "Failed to execute the method - "
              + Thread.currentThread().getStackTrace()[1].getMethodName(),
          e);
    }
    return sortBooks;
  }

  @Override
  public List<Book> sortBookByPrice() {
    List<Book> sortBooks = null;
    try {
      sortBooks = bookRepository.getAll();
      sortBooks.sort(Comparator.comparingInt(Book::getPrice));
      logger.info(
          "Method completed - " + Thread.currentThread().getStackTrace()[1].getMethodName());
    } catch (Exception e) {
      logger.warn(
          "Failed to execute the method - "
              + Thread.currentThread().getStackTrace()[1].getMethodName(),
          e);
    }
    return sortBooks;
  }

  @Override
  public List<Book> sortBookByPublication() {
    List<Book> sortBooks = null;
    try {
      sortBooks = bookRepository.getAll();
      sortBooks.sort(Comparator.comparingInt(Book::getPublication));
      logger.info(
          "Method completed - " + Thread.currentThread().getStackTrace()[1].getMethodName());
    } catch (Exception e) {
      logger.warn(
          "Failed to execute the method - "
              + Thread.currentThread().getStackTrace()[1].getMethodName(),
          e);
    }
    return sortBooks;
  }

  @Override
  public List<Book> sortBookByTitle() {
    List<Book> sortBooks = null;
    try {
      sortBooks = bookRepository.getAll();
      sortBooks.sort(Comparator.comparing(Book::getTitle));
      logger.info(
          "Method completed - " + Thread.currentThread().getStackTrace()[1].getMethodName());
    } catch (Exception e) {
      logger.warn(
          "Failed to execute the method - "
              + Thread.currentThread().getStackTrace()[1].getMethodName(),
          e);
    }
    return sortBooks;
  }

  @Override
  public List<Book> sortOldBookByDate() {
    List<Book> sortBooks = null;
    try {
      sortBooks = getOldBooks();
      sortBooks.sort(Comparator.comparing(Book::getDeliveryDate));
      logger.info(
          "Method completed - " + Thread.currentThread().getStackTrace()[1].getMethodName());
    } catch (Exception e) {
      logger.warn(
          "Failed to execute the method - "
              + Thread.currentThread().getStackTrace()[1].getMethodName(),
          e);
    }
    return sortBooks;
  }

  @Override
  public List<Book> sortOldBookByPrice() {
    List<Book> sortBooks = null;
    try {
      sortBooks = getOldBooks();
      sortBooks.sort(Comparator.comparingInt(Book::getPrice));
      logger.info(
          "Method completed - " + Thread.currentThread().getStackTrace()[1].getMethodName());
    } catch (Exception e) {
      logger.warn(
          "Failed to execute the method - "
              + Thread.currentThread().getStackTrace()[1].getMethodName(),
          e);
    }
    return sortBooks;
  }
}
