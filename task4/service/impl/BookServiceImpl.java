package task4.service.impl;

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
}
