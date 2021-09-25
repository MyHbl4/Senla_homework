package task4.service.impl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import task4.model.Availability;
import task4.model.Book;
import task4.repository.BookRepository;
import task4.repository.RequestRepository;
import task4.service.BookService;

public class BookServiceImpl implements BookService {
  private final BookRepository bookRepository;
  private final RequestRepository requestRepository;

  public BookServiceImpl(BookRepository bookRepository, RequestRepository requestRepository) {
    this.bookRepository = bookRepository;
    this.requestRepository = requestRepository;
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
  public void bookInfoById(int id) {
    Book book = bookRepository.findBookById(id);
    System.out.println(
        "Book ID: "
            + id
            + ", Title: "
            + book.getTitle()
            + ", Author: "
            + book.getAuthor()
            + ", Price: "
            + book.getPrice()
            + ", Availability: "
            + book.getAvailability());
  }

  @Override
  public void sortBookByPrice() {
    bookRepository.getAll().sort((b1, b2) -> b1.getPrice() - b2.getPrice());
    bookRepository.getAll().stream().forEach(System.out::println);
  }

  @Override
  public void sortBookByAvailability() {
    bookRepository.getAll().sort((b1, b2) -> b1.getAvailability().compareTo(b2.getAvailability()));
    bookRepository.getAll().stream().forEach(System.out::println);
  }

  @Override
  public void sortBookByPublishedDate() {
    bookRepository.getAll().sort((b1, b2) -> b1.getPublication() - b2.getPublication());
    bookRepository.getAll().stream().forEach(System.out::println);
  }

  @Override
  public void sortBookByTitle() {
    bookRepository.getAll().sort((b1, b2) -> b1.getTitle().compareTo(b2.getTitle()));
    bookRepository.getAll().stream().forEach(System.out::println);
  }

  @Override
  public void sortOldBookByDeliveryDate() {
    bookRepository
        .getOldBooks()
        .sort((b1, b2) -> b1.getDeliveryDate().compareTo(b2.getDeliveryDate()));
    bookRepository.getOldBooks().stream().forEach(System.out::println);
  }

  @Override
  public void sortOldBookByPrice() {
    bookRepository.getOldBooks().sort((b1, b2) -> b1.getPrice() - b2.getPrice());
    bookRepository.getOldBooks().stream().forEach(System.out::println);
  }

  //  BufferedReader reader = new BufferedReader(new FileReader(fileName));
  @Override
  public void updateBookCsv() {
    String fileName = "bookdata.csv";
    try {
      PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(fileName)));
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
      //      JOptionPane.showMessageDialog(null,"BookDataSource updated");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void downloadBookCsv() {
    String fileName = "bookdata.csv";
    try {
      try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
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
      e.printStackTrace();
    }
    System.out.println("book csv downloaded");
  }
}
