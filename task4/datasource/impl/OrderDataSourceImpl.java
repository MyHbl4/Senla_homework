package task4.datasource.impl;

import java.util.ArrayList;
import java.util.List;
import task4.DI.annotations.InjectByType;
import task4.datasource.OrderDataSource;
import task4.jdbc.OrderDAO;
import task4.model.Order;

public class OrderDataSourceImpl implements OrderDataSource {
  private final List<Order> orders = new ArrayList<>();
  @InjectByType private OrderDAO orderDAO;

  public OrderDataSourceImpl() {
    initData();
  }

  public void initData() {}

  @Override
  public List<Order> getOrders() {
    return orderDAO.readAll();
  }

  @Override
  public Order findOrderById(int id) {
    return orderDAO.read(id);
  }
}
