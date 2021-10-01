package task4.util;

import java.util.List;
import task4.model.Book;
import task4.model.Order;
import task4.model.Request;

public class PrintOut {

  public void printBook(List<Book> books) {
    books.forEach(System.out::println);
  }

  public void printOrder(List<Order> orders) {
    orders.forEach(System.out::println);
  }

  public void printRequest(List<Request> requests) {
    requests.forEach(System.out::println);
  }
}
