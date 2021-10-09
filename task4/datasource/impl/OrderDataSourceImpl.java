package task4.datasource.impl;

import java.util.ArrayList;
import java.util.List;
import task4.datasource.OrderDataSource;
import task4.model.Order;

public class OrderDataSourceImpl implements OrderDataSource {
  private final List<Order> orders = new ArrayList<>();

  public OrderDataSourceImpl() {
    initData();
  }

  public void initData() {}

  @Override
  public List<Order> getOrders() {
    return orders;
  }
}
