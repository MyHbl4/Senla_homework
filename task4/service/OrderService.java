package task4.service;

import java.util.List;
import task4.model.Order;

public interface OrderService {

  Order findOrderById(int id);

  void addRequest(String request);

  void addOrder(Order order);

  void closeOrder(int id);

  void cancelOrder(int id);

  int getAllPriceOfSoldBooks(int months);

  List<Order> getAll();

  List<Order> getCompletedOrderList(int months);

  void updateOrderCsv();

  void downloadOrderCsv();
}
