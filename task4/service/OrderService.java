package task4.service;

import task4.model.Order;

public interface OrderService {

  Order findOrderById(int id);

  void addRequest(String request);

  void addOrder(Order order);

  void closeOrder(int id);

  void cancelOrder(int id);

  void getAllPriceOfSoldBooks(int months);

  int getCompletedOrder(int months);

  void sortOrderByStatus();

  void sortOrderByPrice();

  void sortOrderByExecutionDate();

  void sortCompletedOrderByPrice(int months);

  void sortCompletedOrderByExecutionDate(int months);

  void updateOrderCsv();

  void downloadOrderCsv();
}
