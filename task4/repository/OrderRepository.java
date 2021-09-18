package task4.repository;

import task4.model.Order;
import task4.util.OrderArrayList;

public interface OrderRepository {

  OrderArrayList getAll();

  Order findOrderById(int id);

  Order[] getArray(OrderArrayList orders);

  int getCountCompletedOrder();

  Order[] getArrayCompletedOrder(int months);

}
