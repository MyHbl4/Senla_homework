package com.moon.senla;

import java.util.List;

import com.moon.senla.entity.Order;

public interface OrderRepository {

  List<Order> getAll();

  Order findOrderById(int id);

  boolean checkBooksInOrder(Order order);
}
