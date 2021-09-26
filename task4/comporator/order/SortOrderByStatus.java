package task4.comporator.order;

import java.util.Comparator;
import task4.model.Order;

public class SortOrderByStatus implements Comparator<Order> {

  @Override
  public int compare(Order o1, Order o2) {
    return o1.getOrderStatus().compareTo(o2.getOrderStatus());
  }
}
