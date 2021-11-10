package com.moon.senla.impl;

import com.moon.senla.Availability;
import com.moon.senla.Book;
import com.moon.senla.Order;
import com.moon.senla.OrderDAO;
import com.moon.senla.OrderRepository;
import com.moon.senla.annotations.InjectByType;
import java.util.List;

public class OrderRepositoryImpl implements OrderRepository {
  @InjectByType
  private OrderDAO orderDAO;

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
    boolean availability = true;
    for (Book book : order.getBooks()) {
      if (book.getAvailability().equals(Availability.OUT_OF_STOCK)) {
        availability = false;
      }
    }
    return availability;
  }
}
