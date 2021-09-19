package task4.service;

import task4.model.Order;
import task4.model.Request;

public interface OrderService {

  void  addRequest(String request);

  void addOrder(Order order);

  void closeOrder(int id);

  void cancelOrder(int id);

  void printOrderRepository();

  int getCountSoldBooks(int months);

  int getPriceOfSoldBooksInOrder(int id);

  int getAllPriceOfSoldBooks(int months);

  void orderInfoById(int id);

  void outputArray(Order[] orders);

  Order[] sortOrderByStatus();

  Order[] sortOrderByPrice();

  Order[] sortOrderByExecutionDate();

  Order[] sortCompletedOrderByPrice(int months);

  Order[] sortCompletedOrderByExecutionDate(int months);
}
