package task4;

import java.lang.reflect.InvocationTargetException;
import task4.DI.injector.BeanFactory;
import task4.UI.MenuController;
import task4.UI.action.BookStoreManager;
import task4.UI.action.Manager;
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

  public static void main(String[] args){
//    BookDataSource bookDataSource = BeanFactory.getInstance().getBean(BookDataSource.class);
//    OrderDataSource orderDataSource = BeanFactory.getInstance().getBean(OrderDataSource.class);
//    RequestDataSource requestDataSource = BeanFactory.getInstance().getBean(RequestDataSource.class);
//    BookRepository bookRepository = BeanFactory.getInstance().getBean(BookRepositoryImpl.class);
//    OrderRepository orderRepository = BeanFactory.getInstance().getBean(OrderRepositoryImpl.class);
//    RequestRepository requestRepository =
//        BeanFactory.getInstance().getBean(RequestRepositoryImpl.class);
//    BookService bookService =
//        BeanFactory.getInstance().getBean(BookServiceImpl.class);
//    OrderService orderService =
//        BeanFactory.getInstance().getBean(OrderServiceImpl.class);
//    RequestService requestService = BeanFactory.getInstance().getBean(RequestServiceImpl.class);
    Manager manager = BeanFactory.getInstance().getBean(
        BookStoreManager.class);
    MenuController menuController = new MenuController(manager);
    menuController.run();
  }
}
