package task4.datasource;

import task4.model.Order;
import task4.other.OrderArrayList;

public class OrderDataSourceImpl implements OrderDataSource {
  private final OrderArrayList orders = new OrderArrayList();

  public OrderDataSourceImpl() {
    initData();
  }

  public void initData() {

    orders.add(new Order(1, 2, new int[] {1, 2, 5}));
    orders.add(new Order(2, 3, new int[] {3, 4, 7}));
    orders.add(new Order(3, 1, new int[] {2, 6, 8}));
  }

  @Override
  public OrderArrayList getOrders() {
    return orders;
  }
}
