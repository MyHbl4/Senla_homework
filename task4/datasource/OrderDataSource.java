package task4.datasource;

import java.util.List;
import task4.model.Order;

public interface OrderDataSource {
  List<Order> getOrders();
}
