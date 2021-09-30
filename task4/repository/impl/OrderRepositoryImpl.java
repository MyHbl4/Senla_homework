package task4.repository.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import task4.datasource.BookDataSource;
import task4.datasource.OrderDataSource;
import task4.model.Order;
import task4.model.OrderStatus;
import task4.repository.OrderRepository;

public class OrderRepositoryImpl implements OrderRepository {
  private final OrderDataSource orderDataSource;
  private final BookDataSource bookDataSource;

  public OrderRepositoryImpl(OrderDataSource orderDataSource, BookDataSource bookDataSource) {
    this.orderDataSource = orderDataSource;
    this.bookDataSource = bookDataSource;
  }

  @Override
  public List<Order> getAll() {
    return orderDataSource.getOrders();
  }

  @Override
  public Order findOrderById(int id) {
    Order order = null;
    for (int i = 0; i < orderDataSource.getOrders().size(); i++) {
      if (orderDataSource.getOrders().get(i).getId() == id) {
        order = orderDataSource.getOrders().get(i);
      }
    }
    return order;
  }


}
