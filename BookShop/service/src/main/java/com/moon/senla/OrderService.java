package com.moon.senla;

import com.moon.senla.entity.Order;
import java.util.List;

public interface OrderService {

    void addOrder(Order order);

    void checkBooksForRequest(Order order);

    boolean checkBooksInOrder(Order order);

    void closeOrder(int id);

    void cancelOrder(int id);

    List<Order> getCompletedOrderList(int months);

    List<Order> sortCompletedOrderByExecutionDate(int months);

    List<Order> sortCompletedOrderByPrice(int months);

    List<Order> sortOrderByExecutionDate();

    List<Order> sortOrderByPrice();

    List<Order> sortOrderByStatus();

    void create(Order entity);

    Order read(int id);

    List<Order> readAll();

    void update(Order entity);

    void delete(Order entity);
}
