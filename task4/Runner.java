package task4;

import task4.DI.factory.ObjectFactory;
import task4.DI.factory.Test;
import task4.UI.MenuController;

public class Runner {

  public static void main(String[] args) {
//    BookDataSource bookDataSource = ObjectFactory.getInstance().createObject(BookDataSource.class);
//    OrderDataSource orderDataSource = new OrderDataSourceImpl();
//    RequestDataSource requestDataSource = new RequestDataSourceImpl();
//    BookRepository bookRepository = new BookRepositoryImpl();
//    OrderRepository orderRepository = new OrderRepositoryImpl();
//    RequestRepository requestRepository =
//        new RequestRepositoryImpl(requestDataSource, bookDataSource);
//    BookService bookService =
//        new BookServiceImpl(bookRepository, requestRepository, orderRepository);
//    OrderService orderService =
//        new OrderServiceImpl(orderRepository, bookRepository, requestRepository);
//    RequestService requestService = new RequestServiceImpl(requestRepository, bookRepository);
//    Manager manager = new BookStoreManager(bookService, orderService, requestService);
    MenuController menuController = ObjectFactory.getInstance().createObject(MenuController.class);
    Test test = ObjectFactory.getInstance().createObject(Test.class);
    test.recommend();
    menuController.run();
  }
}
