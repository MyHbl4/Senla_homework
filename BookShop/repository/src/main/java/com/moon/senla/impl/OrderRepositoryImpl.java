package com.moon.senla.impl;

import com.moon.senla.BookDAO;
import com.moon.senla.enums.Availability;
import com.moon.senla.Book;
import com.moon.senla.Order;
import com.moon.senla.OrderDAO;
import com.moon.senla.OrderRepository;
import com.moon.senla.annotations.InjectByType;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OrderRepositoryImpl implements OrderRepository {
  @InjectByType
  private OrderDAO orderDAO;
  private static final Logger logger = LoggerFactory.getLogger(OrderRepositoryImpl.class);
  @Override
  public List<Order> getAll() {
    return orderDAO.readAll();
  }

  @Override
  public Order findOrderById(int id) {
    return orderDAO.read(id);
  }

  @Override
  public boolean checkBooksInOrder(Order order) {
    boolean availability = false;
    try {
      availability = true;
      for (Book book : order.getBooks()) {
        if (book.getAvailability().equals(Availability.OUT_OF_STOCK)) {
          availability = false;
        }
      }
      logger.info("Method completed - " + Thread.currentThread().getStackTrace()[1].getMethodName());
    } catch (Exception e) {
      logger.warn("Failed to execute the method - " + Thread.currentThread().getStackTrace()[1].getMethodName(), e);
    }
    return availability;
  }
}
