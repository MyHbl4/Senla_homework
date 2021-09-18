package task4.comporator.order;

import java.util.Comparator;
import task4.model.Order;

public class SortOrderByExecutionDate implements Comparator<Order> {

  @Override
  public int compare(Order o1, Order o2) {
    if (o1.getExecution().isAfter(o2.getExecution())) {
      return -1;
    } else if (o1.getExecution().isBefore(o2.getExecution())) {
      return 1;
    } else {
      return 0;
    }
  }
}
