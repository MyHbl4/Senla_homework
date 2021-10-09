package task4.repository;

import java.util.List;
import task4.model.Order;

public interface OrderRepository {

  List<Order> getAll();

  Order findOrderById(int id);
}
