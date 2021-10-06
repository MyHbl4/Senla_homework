package task4.repository.impl;

import java.util.List;
import task4.datasource.BookDataSource;
import task4.datasource.OrderDataSource;
import task4.enums.Availability;
import task4.model.Book;
import task4.model.Order;
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
    return orderDataSource.findOrderById(id);
  }

  @Override
  public boolean checkBooksInOrder(Order order) {
    boolean availability = true;
    for (Book book : order.getBooks()) {
      if (book.getAvailability().equals(Availability.OUT_OF_STOCK)) {
        availability = false;
      }
    }
    return availability;
  }
}
