package com.moon.senla.api;

import com.moon.senla.entity.Order;

public interface IOrderDao extends GenericDao<Order> {

    void createOrderBooks(Order order);
}
