package com.moon.senla.api;

import java.util.List;

import com.moon.senla.entity.Book;
import com.moon.senla.entity.Order;

public interface IOrderDao extends GenericDao<Order> {

    void createOrderBooks(Order order);

    List<Book> readBooks(Integer id);
}
