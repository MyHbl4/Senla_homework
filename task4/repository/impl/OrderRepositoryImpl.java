package task4.repository.impl;

import java.time.LocalDate;
import task4.datasource.BookDataSource;
import task4.datasource.OrderDataSource;
import task4.model.Order;
import task4.model.OrderStatus;
import task4.repository.OrderRepository;
import task4.util.OrderArrayList;

public class OrderRepositoryImpl implements OrderRepository {
  private final OrderDataSource orderDataSource;
  private final BookDataSource bookDataSource;

  public OrderRepositoryImpl(OrderDataSource orderDataSource, BookDataSource bookDataSource) {
    this.orderDataSource = orderDataSource;
    this.bookDataSource = bookDataSource;
  }

  @Override
  public OrderArrayList getAll() {
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

  @Override
  public Order[] getArray(OrderArrayList ordersList) {
    Order[] orders = new Order[ordersList.size()];
    for (int i = 0; i < ordersList.size(); i++) {
      orders[i] = ordersList.get(i);
    }
    return orders;
  }

  @Override
  public int getCountCompletedOrder() {
    int count = 0;
    for (int i = 0; i < orderDataSource.getOrders().size(); i++) {
      if (orderDataSource.getOrders().get(i).getOrderStatus().equals(OrderStatus.COMPLETED)) {
        count++;
      }
    }
    return count;
  }

  @Override
  public Order[] getArrayCompletedOrder(int months) {
    Order[] orders = new Order[getCountCompletedOrder()];
    int indexOrder = 0;
    for (int i = 0; i < orderDataSource.getOrders().size(); i++) {
      if (orderDataSource.getOrders().get(i).getOrderStatus().equals(OrderStatus.COMPLETED)
          && orderDataSource
              .getOrders()
              .get(i)
              .getExecution()
              .isAfter(LocalDate.now().minusMonths(months))) {
        orders[indexOrder] = orderDataSource.getOrders().get(i);
        indexOrder++;
      }
    }
    return orders;
  }
}
