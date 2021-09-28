package task4.UI.action;

import task4.datasource.BookDataSource;
import task4.datasource.OrderDataSource;
import task4.datasource.RequestDataSource;
import task4.datasource.impl.BookDataSourceImpl;
import task4.datasource.impl.OrderDataSourceImpl;
import task4.datasource.impl.RequestDataSourceImpl;
import task4.model.Book;
import task4.model.Order;
import task4.model.Request;
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

public class Manager implements BookService, OrderService, RequestService {
  private static final BookDataSource bookDataSource = new BookDataSourceImpl();
  private static final OrderDataSource orderDataSource = new OrderDataSourceImpl();
  private static final RequestDataSource requestDataSource = new RequestDataSourceImpl();
  private static final BookRepository bookRepository = new BookRepositoryImpl(bookDataSource);
  private static final OrderRepository orderRepository =
      new OrderRepositoryImpl(orderDataSource, bookDataSource);
  private static final RequestRepository requestRepository =
      new RequestRepositoryImpl(requestDataSource);
  private static final BookService bookService =
      new BookServiceImpl(bookRepository, requestRepository);
  private static final OrderService orderService =
      new OrderServiceImpl(orderRepository, bookRepository, requestRepository);
  private static final RequestService requestService = new RequestServiceImpl(requestRepository);
  private static Manager instance;

  public Manager() {}

//  public static void main(String[] args) {
//    System.out.println(    new Manager().findOrderById(1));
//  }
//  public Request findRequestById(int id){return requestRepository.findRequestById(id);}

  public Order findOrderById(int id){return orderRepository.findOrderById(id);}

  public Book findBookById(int id) {
    return bookRepository.findBookById(id);
  }

  @Override
  public void addBook(Book book) {
    bookService.addBook(book);
  }

  @Override
  public void removeBook(int id) {
    bookService.removeBook(id);
  }

  @Override
  public void bookInfoById(int id) {
    bookService.bookInfoById(id);
  }

  @Override
  public void sortBookByAvailability() {
    bookService.sortBookByAvailability();
  }

  @Override
  public void sortBookByPrice() {
    bookService.sortBookByPrice();
  }

  @Override
  public void sortBookByPublishedDate() {
    bookService.sortBookByPublishedDate();
  }

  @Override
  public void sortBookByTitle() {
    bookService.sortBookByTitle();
  }

  @Override
  public void sortOldBookByDeliveryDate() {
    bookService.sortOldBookByDeliveryDate();
  }

  @Override
  public void sortOldBookByPrice() {
    bookService.sortOldBookByPrice();
  }

  @Override
  public void updateBookCsv() {
    bookService.updateBookCsv();
  }

  @Override
  public void downloadBookCsv() {
    bookService.downloadBookCsv();
  }

  @Override
  public void addRequest(String request) {
    orderService.addRequest(request);
  }

  @Override
  public void addOrder(Order order) {
    orderService.addOrder(order);
  }

  @Override
  public void closeOrder(int id) {
    orderService.closeOrder(id);
  }

  @Override
  public void cancelOrder(int id) {
    orderService.closeOrder(id);
  }

  @Override
  public void getAllPriceOfSoldBooks(int months) {
    orderService.getAllPriceOfSoldBooks(months);
  }

  @Override
  public void getCompletedOrder(int months) {
    orderService.getCompletedOrder(months);
  }

  @Override
  public void orderInfoById(int id) {
    orderService.orderInfoById(id);
  }

  @Override
  public void sortOrderByStatus() {
    orderService.sortOrderByStatus();
  }

  @Override
  public void sortOrderByPrice() {
    orderService.sortOrderByPrice();
  }

  @Override
  public void sortOrderByExecutionDate() {
    orderService.sortOrderByExecutionDate();
  }

  @Override
  public void sortCompletedOrderByPrice(int months) {
    orderService.sortCompletedOrderByPrice(months);
  }

  @Override
  public void sortCompletedOrderByExecutionDate(int months) {
    orderService.sortCompletedOrderByExecutionDate(months);
  }

  @Override
  public void updateOrderCsv() {
    orderService.updateOrderCsv();
  }

  @Override
  public void downloadOrderCsv() {
    orderService.downloadOrderCsv();
  }

  @Override
  public void sortRequestByCount() {
    requestService.sortRequestByCount();
  }

  @Override
  public void sortRequestByTitle() {
    requestService.sortRequestByTitle();
  }

  @Override
  public void updateRequestCsv() {
    requestService.updateRequestCsv();
  }

  @Override
  public void downloadRequestCsv() {
    requestService.downloadRequestCsv();
  }
}
