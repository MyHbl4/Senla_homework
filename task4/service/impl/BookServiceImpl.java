package task4.service.impl;

import static task4.UI.Constant.FILE_BOOKS;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import task4.model.Availability;
import task4.model.Book;
import task4.repository.BookRepository;
import task4.repository.RequestRepository;
import task4.service.BookService;
import task4.util.PropertyFile;

public class BookServiceImpl implements BookService {
  private final BookRepository bookRepository;
  private final RequestRepository requestRepository;

  public BookServiceImpl(BookRepository bookRepository, RequestRepository requestRepository) {
    this.bookRepository = bookRepository;
    this.requestRepository = requestRepository;
  }

  @Override
  public Book findBookById(int id) {
    return bookRepository.findBookById(id);
  }

  @Override
  public void addBook(Book book) {
    bookRepository.getAll().add(book);
    for (int i = 0; i < requestRepository.getAll().size(); i++) {
      if (book.getTitle().equals(requestRepository.getAll().get(i).getTitle())
          && requestRepository.getAll().get(i).getCount() > 0) {
        requestRepository
            .getAll()
            .get(i)
            .setCount(requestRepository.getAll().get(i).getCount() - 1);
      }
    }
  }

  @Override
  public void removeBook(int id) {
    bookRepository.findBookById(id).setAvailability(Availability.OUT_OF_STOCK);
  }

  @Override
  public List<Book> getAll() {
    return bookRepository.getAll();
  }

  @Override
  public List<Book> getOldBooks() {
    List<Book> oldBooks = new ArrayList<>();
    for (Book book : bookRepository.getAll()) {
      if (book.getDeliveryDate().isBefore(LocalDate.now().minusMonths(
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
  public void updateBookCsv() {
    try {
      PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(FILE_BOOKS)));
      for (Book book : bookRepository.getAll()) {
        writer.println(
            book.getId()
                + "|"
                + book.getTitle()
                + "|"
                + book.getAuthor()
                + "|"
                + book.getPrice()
                + "|"
                + book.getPublication()
                + "|"
                + book.getAvailability()
                + "|"
                + book.getDeliveryDate());
      }
      writer.flush();
      writer.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void downloadBookCsv() {
    try {
      try (BufferedReader reader = new BufferedReader(new FileReader(FILE_BOOKS))) {
        String someBook;
        bookRepository.getAll().clear();
        while ((someBook = reader.readLine()) != null) {
          String[] values = someBook.split("\\|");
          bookRepository
              .getAll()
              .add(
                  new Book(
                      Long.parseLong(values[0]),
                      values[1],
                      values[2],
                      Integer.parseInt(values[3]),
                      Integer.parseInt(values[4]),
                      Availability.valueOf(values[5]),
                      LocalDate.parse(values[6])));
        }
      }
    } catch (IOException e) {
      System.out.println("Loading error");
    }
  }
}
