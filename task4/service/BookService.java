package task4.service;

import task4.model.Book;

public interface BookService {

  void addBook(Book book);

  void removeBook(int id);

  void bookInfoById(int id);

  void sortBookByAvailability();

  void sortBookByPrice();

  void sortBookByPublishedDate();

  void sortBookByTitle();

  void sortOldBookByDeliveryDate();

  void sortOldBookByPrice();

  void updateBookCsv();

  void downloadBookCsv();

}
