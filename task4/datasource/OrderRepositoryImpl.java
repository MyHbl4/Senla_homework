package task4.datasource;

import task4.model.Order;

public class OrderRepositoryImpl implements OrderRepository {
  private final OrderDataSource orderDataSource;

  public OrderRepositoryImpl(OrderDataSource orderDataSource) {
    this.orderDataSource = orderDataSource;
  }

  @Override
  public void addOrder(Order order) {
    System.out.println("Order added!");
    orderDataSource.getOrders().add(order);
  }

  @Override
  public void closeOrder(int id) {
    orderDataSource.getOrders().get(id - 1).setOrderStatusCompleate();
    System.out.println("The order is closed!");
  }

  @Override
  public void cancelOrder(int id) {
    orderDataSource.getOrders().get(id - 1).setOrderStatusCanceled();
    System.out.println("The order was canceled!");
  }

  @Override
  public void printOrderRepository() {
    System.out.println("List of all orders:");
    orderDataSource.getOrders().print();
  }
}
