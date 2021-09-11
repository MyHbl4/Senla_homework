package task4.datasource;

import task4.model.Order;

public interface OrderRepository {

    void addOrder(Order order);

    void closeOrder(Order order);

    void cancelOrder(Order order);

    void printOrderRepository();
}
