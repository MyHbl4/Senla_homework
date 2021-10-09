package task4.service;

import java.util.List;
import task4.model.Order;

public interface OrderService {

  Order findOrderById(int id);

  void addOrder(Order order);

  void checkBooksForRequest(Order order);

  boolean checkBooksInOrder(Order order);

  void closeOrder(int id);

  void cancelOrder(int id);

  int getAllPriceOfSoldBooks(int months);

  List<Order> getAll();

  List<Order> getCompletedOrderList(int months);

  List<Order> sortCompletedOrderByExecutionDate(int months);

  List<Order> sortCompletedOrderByPrice(int months);

  List<Order> sortOrderByExecutionDate();

  List<Order> sortOrderByPrice();

  List<Order> sortOrderByStatus();

  void writeOrderBd();

  void readOrderBd();

  void updateOrderCsv();

  void downloadOrderCsv();
}
