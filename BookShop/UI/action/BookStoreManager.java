package task4.UI.action;

import task4.DI.annotations.InjectByType;
import task4.service.BookService;
import task4.service.OrderService;
import task4.service.RequestService;

public class BookStoreManager implements Manager {
  @InjectByType private BookService bookService;
  @InjectByType private OrderService orderService;
  @InjectByType private RequestService requestService;

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
