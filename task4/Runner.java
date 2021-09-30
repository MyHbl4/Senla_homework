package task4;

import task4.UI.MenuController;
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

public class Runner {

  public static void main(String[] args) {
    BookDataSource bookDataSource = new BookDataSourceImpl();
    OrderDataSource orderDataSource = new OrderDataSourceImpl();
    RequestDataSource requestDataSource = new RequestDataSourceImpl();
    BookRepository bookRepository = new BookRepositoryImpl(bookDataSource);
    OrderRepository orderRepository = new OrderRepositoryImpl(orderDataSource, bookDataSource);
    RequestRepository requestRepository = new RequestRepositoryImpl(requestDataSource);
    BookService bookService = new BookServiceImpl(bookRepository, requestRepository);
    OrderService orderService =
        new OrderServiceImpl(orderRepository, bookRepository, requestRepository);
    RequestService requestService = new RequestServiceImpl(requestRepository);
    new MenuController().run();
  }
}
