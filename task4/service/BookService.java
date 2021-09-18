package task4.service;

import task4.model.Book;

public interface BookService {

  void addBook(Book book);

  void inStock(int id);

  void outOfStock(int id);

  void removeBook(int id);

  void printBookRepository();

  void bookInfoById(int id);

  void outputArray(Book[] books);

  Book[] sortBookByAvailability();

  Book[] sortBookByPrice();

  Book[] sortBookByPublishedDate();

  Book[] sortBookByTitle();

  Book[] sortOldBookByDeliveryDate();

  Book[] sortOldBookByPrice();
}
