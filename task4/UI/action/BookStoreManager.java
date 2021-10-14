package task4.UI.action;

import task4.DI.factory.ObjectFactory;
import task4.service.BookService;
import task4.service.OrderService;
import task4.service.RequestService;

public class BookStoreManager implements Manager {
  private final BookService bookService = ObjectFactory.getInstance().createObject(BookService.class);
  private final OrderService orderService = ObjectFactory.getInstance().createObject(OrderService.class);
  private final RequestService requestService = ObjectFactory.getInstance().createObject(RequestService.class);


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
