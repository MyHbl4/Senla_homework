package task4.service;

import task4.model.Order;

public interface OrderService {

  void addRequest(String request);

  void addOrder(Order order);

  void closeOrder(int id);

  void cancelOrder(int id);

  void getAllPriceOfSoldBooks(int months);

  void getCompletedOrder(int months);

  void orderInfoById(int id);

  void sortOrderByStatus();

  void sortOrderByPrice();

  void sortOrderByExecutionDate();

  void sortCompletedOrderByPrice(int months);

  void sortCompletedOrderByExecutionDate(int months);
}
