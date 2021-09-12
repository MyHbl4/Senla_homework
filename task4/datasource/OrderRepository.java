package task4.datasource;

import task4.model.Order;

public interface OrderRepository {

  void addOrder(Order order);

  void closeOrder(int id);

  void cancelOrder(int id);

  void printOrderRepository();
}
