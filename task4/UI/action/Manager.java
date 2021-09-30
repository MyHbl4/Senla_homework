package task4.UI.action;

import task4.datasource.BookDataSource;
import task4.datasource.OrderDataSource;
import task4.datasource.RequestDataSource;
import task4.datasource.impl.BookDataSourceImpl;
import task4.datasource.impl.OrderDataSourceImpl;
import task4.datasource.impl.RequestDataSourceImpl;
import task4.repository.BookRepository;
import task4.repository.OrderRepository;
import task4.repository.RequestRepository;
import task4.repository.impl.BookRepositoryImpl;
import task4.repository.impl.OrderRepositoryImpl;
import task4.repository.impl.RequestRepositoryImpl;
import task4.service.BookService;
import task4.service.OrderService;
import task4.service.RequestService;
import task4.service.impl.BookServiceImpl;
import task4.service.impl.OrderServiceImpl;
import task4.service.impl.RequestServiceImpl;

public class Manager {

  private static final BookDataSource bookDataSource = new BookDataSourceImpl();
  private static final OrderDataSource orderDataSource = new OrderDataSourceImpl();
  private static final RequestDataSource requestDataSource = new RequestDataSourceImpl();
  private static final BookRepository bookRepository = new BookRepositoryImpl(bookDataSource);
  private static final OrderRepository orderRepository =
      new OrderRepositoryImpl(orderDataSource, bookDataSource);
  private static final RequestRepository requestRepository =
      new RequestRepositoryImpl(requestDataSource);
  private final BookService bookService = new BookServiceImpl(bookRepository, requestRepository);
  private final OrderService orderService =
      new OrderServiceImpl(orderRepository, bookRepository, requestRepository);
  private final RequestService requestService = new RequestServiceImpl(requestRepository);

  public Manager() {}

  public BookService getBookService() {    return bookService;
  }

  public OrderService getOrderService() {
    return orderService;
  }

  public RequestService getRequestService() {
    return requestService;
  }
}
