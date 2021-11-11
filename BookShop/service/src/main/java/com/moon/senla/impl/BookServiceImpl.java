package com.moon.senla.impl;

import com.moon.senla.Book;
import com.moon.senla.BookDAO;
import com.moon.senla.BookRepository;
import com.moon.senla.BookService;
import com.moon.senla.Order;
import com.moon.senla.OrderRepository;
import com.moon.senla.Request;
import com.moon.senla.RequestRepository;
import com.moon.senla.annotations.InjectByType;
import com.moon.senla.annotations.InjectProperty;
import com.moon.senla.enums.OrderStatus;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class BookServiceImpl implements BookService {
  @InjectByType private BookRepository bookRepository;
  @InjectByType private RequestRepository requestRepository;
  @InjectByType private OrderRepository orderRepository;
  @InjectByType private BookDAO bookDAO;
  @InjectProperty private String FUNCTION_ORDER;
  @InjectProperty private String MONTHS_STALE_BOOKS;

  @Override
  public Book findBookById(int id) {
    return bookRepository.findBookById(id);
  }

  @Override
  public void addBook(Book book) {
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
  }

  private void checkOrder() {
    for (Order order : orderRepository.getAll()) {
      if (orderRepository.checkBooksInOrder(order)) {
        order.setOrderStatusCompleate();
        bookRepository.removeBooks(order.getBooks());
      }
    }
  }

  @Override
  public void removeBook(int id) {
    bookRepository.removeBook(id);
  }

  @Override
  public boolean checkBookInRequests(Book book) {
    boolean availability = false;
    for (Request request : requestRepository.getAll()) {
      if (book.getTitle().equals(request.getTitle()) && request.getCount() > 0) {
        availability = true;
      }
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
    if (checkBookInRequests(book)) {
      for (Request request : requestRepository.getAll()) {
        if (book.getTitle().equals(request.getTitle()) && request.getCount() > 0) {
          request.setCount(request.getCount() - 1);
          break;
        }
      }
    }
  }

  @Override
  public List<Book> getAll() {
    return bookRepository.getAll();
  }

  @Override
  public List<Book> getOldBooks() {
    List<Book> oldBooks = new ArrayList<>();
    for (Book book : bookRepository.getAll()) {
      if (book.getDeliveryDate()
          .isBefore(LocalDate.now().minusMonths(Long.parseLong(MONTHS_STALE_BOOKS)))) {
        oldBooks.add(book);
      }
    }
    return oldBooks;
  }

  @Override
  public List<Book> sortBookByAvailability() {
    List<Book> sortBooks = bookRepository.getAll();
    sortBooks.sort(Comparator.comparing(Book::getAvailability));
    return sortBooks;
  }

  @Override
  public List<Book> sortBookByPrice() {
    List<Book> sortBooks = bookRepository.getAll();
    sortBooks.sort(Comparator.comparingInt(Book::getPrice));
    return sortBooks;
  }

  @Override
  public List<Book> sortBookByPublication() {
    List<Book> sortBooks = bookRepository.getAll();
    sortBooks.sort(Comparator.comparingInt(Book::getPublication));
    return sortBooks;
  }

  @Override
  public List<Book> sortBookByTitle() {
    List<Book> sortBooks = bookRepository.getAll();
    sortBooks.sort(Comparator.comparing(Book::getTitle));
    return sortBooks;
  }

  @Override
  public List<Book> sortOldBookByDate() {
    List<Book> sortBooks = getOldBooks();
    sortBooks.sort(Comparator.comparing(Book::getDeliveryDate));
    return sortBooks;
  }

  @Override
  public List<Book> sortOldBookByPrice() {
    List<Book> sortBooks = getOldBooks();
    sortBooks.sort(Comparator.comparingInt(Book::getPrice));
    return sortBooks;
  }
}
