package com.moon.senla.repository;

import com.moon.senla.entity.Order;
import java.util.List;

public interface OrderRepository {

    List<Order> getAll();

    Order findOrderById(int id);

    boolean checkBooksInOrder(Order order);
}
