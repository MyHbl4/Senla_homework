package task4.UI.action;

import task4.service.BookService;
import task4.service.OrderService;
import task4.service.RequestService;

public class BookStoreManager implements Manager {
  private BookService bookService;
  private OrderService orderService;
  private RequestService requestService;

  public BookStoreManager(
      BookService bookService, OrderService orderService, RequestService requestService) {
    this.bookService = bookService;
    this.orderService = orderService;
    this.requestService = requestService;
  }

  public BookService getBookService() {
    return bookService;
  }

  public OrderService getOrderService() {
    return orderService;
  }

  public RequestService getRequestService() {
    return requestService;
  }
}
