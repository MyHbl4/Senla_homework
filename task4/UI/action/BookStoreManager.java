package task4.UI.action;

import task4.DI.injector.BeanFactory;
import task4.service.BookService;
import task4.service.OrderService;
import task4.service.RequestService;
import task4.service.impl.BookServiceImpl;
import task4.service.impl.OrderServiceImpl;
import task4.service.impl.RequestServiceImpl;

public class BookStoreManager implements Manager {
  private final BookService bookService = BeanFactory.getInstance().getBean(BookServiceImpl.class);
  private final OrderService orderService = BeanFactory.getInstance().getBean(OrderServiceImpl.class);
  private final RequestService requestService =
      BeanFactory.getInstance().getBean(RequestServiceImpl.class);

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
