package task4.service.impl;

import java.util.Arrays;
import task4.comporator.book.SortBookByAvailability;
import task4.comporator.book.SortBookByPrice;
import task4.comporator.book.SortBookByPublishedDate;
import task4.comporator.book.SortBookByTitle;
import task4.comporator.book.SortOldBookByDeliveryDate;
import task4.model.Book;
import task4.repository.BookRepository;
import task4.service.BookService;

public class BookServiceImpl implements BookService {
  private final BookRepository bookRepository;

  public BookServiceImpl(BookRepository bookRepository) {
    this.bookRepository = bookRepository;
  }

  @Override
  public void addBook(Book book) {
    bookRepository.getAll().add(book);
    System.out.println("The book has been added!");
  }

  @Override
  public void inStock(int id) {
    bookRepository.findBookById(id).setBookStatusInStock();
    System.out.println("The book is in stock!");
  }

  @Override
  public void outOfStock(int id) {
    bookRepository.findBookById(id).setBookStatusOutOfStock();
    System.out.println("The book is out of stock!");
  }

  @Override
  public void removeBook(int id) {
    for (int i = 0; i < bookRepository.getAll().size(); i++) {
      if (bookRepository.getAll().get(i).getId() == id) {
        bookRepository.getAll().remove(i);
        System.out.println("The book has been deleted!");
      }
    }
  }

  @Override
  public void printBookRepository() {
    System.out.println("List of all books:");
    bookRepository.getAll().print();
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
            + ", Genre: "
            + book.getGenre()
            + ", Price: "
            + book.getPrice()
            + ", Availability: "
            + book.getAvailability());
  }

  @Override
  public void outputArray(Book[] books) {
    for (Book book : books) {
      System.out.println(book);
    }
  }

  @Override
  public Book[] sortBookByPrice() {
    Book[] sortBook = bookRepository.getArrayBook();
    Arrays.sort(sortBook, new SortBookByPrice());
    return sortBook;
  }

  @Override
  public Book[] sortBookByAvailability() {
    Book[] sortBook = bookRepository.getArrayBook();
    Arrays.sort(sortBook, new SortBookByAvailability());
    return sortBook;
  }

  @Override
  public Book[] sortBookByPublishedDate() {
    Book[] sortBook = bookRepository.getArrayBook();
    Arrays.sort(sortBook, new SortBookByPublishedDate());
    return sortBook;
  }

  @Override
  public Book[] sortBookByTitle() {
    Book[] sortBook = bookRepository.getArrayBook();
    Arrays.sort(sortBook, new SortBookByTitle());
    return sortBook;
  }

  @Override
  public Book[] sortOldBookByDeliveryDate() {
    Book[] sortBook = bookRepository.getArrayOldBook();
    Arrays.sort(sortBook, new SortOldBookByDeliveryDate());
    return sortBook;
  }

  @Override
  public Book[] sortOldBookByPrice() {
    Book[] sortBook = bookRepository.getArrayOldBook();
    Arrays.sort(sortBook, new SortBookByPrice());
    return sortBook;
  }
}
