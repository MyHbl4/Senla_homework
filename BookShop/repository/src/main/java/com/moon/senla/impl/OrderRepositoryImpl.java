package com.moon.senla.impl;

import java.util.List;

import com.moon.senla.OrderRepository;
import com.moon.senla.entity.Book;
import com.moon.senla.entity.Order;
import com.moon.senla.enums.Availability;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderRepositoryImpl implements OrderRepository {
  private static final Logger logger = LoggerFactory.getLogger(OrderRepositoryImpl.class);
  private OrderDao orderDAO;

  @Autowired
  public OrderRepositoryImpl(OrderDao orderDAO) {
    this.orderDAO = orderDAO;
  }

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
      logger.info(
          "Method completed - " + Thread.currentThread().getStackTrace()[1].getMethodName());
    } catch (Exception e) {
      logger.warn(
          "Failed to execute the method - "
              + Thread.currentThread().getStackTrace()[1].getMethodName(),
          e);
    }
    return availability;
  }
}
