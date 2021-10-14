package task4.service.impl;

import static task4.util.Constant.FILE_BOOKS;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import task4.DI.factory.ObjectFactory;
import task4.datasource.BookDataSource;
import task4.enums.Availability;
import task4.enums.OrderStatus;
import task4.model.Book;
import task4.model.Order;
import task4.model.Request;
import task4.repository.BookRepository;
import task4.repository.OrderRepository;
import task4.repository.RequestRepository;
import task4.service.BookService;
import task4.util.PropertyFile;

public class BookServiceImpl implements BookService {
  private final BookRepository bookRepository = ObjectFactory.getInstance().createObject(
      BookRepository.class);
  private final RequestRepository requestRepository = ObjectFactory.getInstance().createObject(RequestRepository.class);
  private final OrderRepository orderRepository = ObjectFactory.getInstance().createObject(OrderRepository.class);

  @Override
  public Book findBookById(int id) {
    return bookRepository.findBookById(id);
  }

  @Override
  public void addBook(Book book) {
    if (!bookRepository.checkBookInBooks(book)) {
      bookRepository.restoreBook(book);
    } else {
      bookRepository.getAll().add(book);
    }
    if (checkBookInRequests(book)) {
      if (checkBookInOrders(book)
          && (new PropertyFile().getPropertyValue("FUNCTION_ORDER").equals("on"))) {
        for (Order order : orderRepository.getAll()) {
          if (orderRepository.checkBooksInOrder(order)) {
            order.setOrderStatusCompleate();
            bookRepository.removeBooks(order.getBooks());
          }
        }
      } else {
        removeBookRequest(book);
      }
    }
  }

  @Override
  public void removeBook(int id) {
    bookRepository.findBookById(id).setAvailability(Availability.OUT_OF_STOCK);
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
          .isBefore(
              LocalDate.now()
                  .minusMonths(
                      Long.parseLong(new PropertyFile().getPropertyValue("MONTHS_STALE_BOOKS"))))) {
        oldBooks.add(book);
      }
    }
    return oldBooks;
  }

  @Override
  public List<Book> sortBookByAvailability() {
    List<Book> sortBooks = bookRepository.getAll();
    bookRepository.getAll().sort(Comparator.comparing(Book::getAvailability));
    return sortBooks;
  }

  @Override
  public List<Book> sortBookByPrice() {
    List<Book> sortBooks = bookRepository.getAll();
    bookRepository.getAll().sort(Comparator.comparingInt(Book::getPrice));
    return sortBooks;
  }

  @Override
  public List<Book> sortBookByPublication() {
    List<Book> sortBooks = bookRepository.getAll();
    bookRepository.getAll().sort(Comparator.comparingInt(Book::getPublication));
    return sortBooks;
  }

  @Override
  public List<Book> sortBookByTitle() {
    List<Book> sortBooks = bookRepository.getAll();
    bookRepository.getAll().sort(Comparator.comparing(Book::getTitle));
    return sortBooks;
  }

  @Override
  public List<Book> sortOldBookByDate() {
    List<Book> sortBooks = getOldBooks();
    getOldBooks().sort(Comparator.comparing(Book::getDeliveryDate));
    return sortBooks;
  }

  @Override
  public List<Book> sortOldBookByPrice() {
    List<Book> sortBooks = getOldBooks();
    getOldBooks().sort(Comparator.comparingInt(Book::getPrice));
    return sortBooks;
  }

  @Override
  public void writeBookBd() {
    ObjectMapper mapper = new ObjectMapper();
    try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(FILE_BOOKS)))) {
      List<Book> books = bookRepository.getAll();
      String bookJson = mapper.writeValueAsString(books);
      writer.write(bookJson);
      writer.flush();
    } catch (IOException e) {
      System.out.println("Writing books error");
    }
  }

  @Override
  public void readBookBd() {
    ObjectMapper mapper = new ObjectMapper();
    List<Book> books;
    try (BufferedReader reader = new BufferedReader(new FileReader(FILE_BOOKS))) {
      String bookJson;
      bookRepository.getAll().clear();
      while ((bookJson = reader.readLine()) != null) {
        books = Arrays.asList(mapper.readValue(bookJson, Book[].class));
        for (Book book : books) {
          getAll().add(book);
        }
      }
    } catch (IOException e) {
      System.out.println("Loading books error");
    }
  }
}
