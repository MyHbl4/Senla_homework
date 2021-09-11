package task4.datasource;

import task4.model.Order;

public class OrderrepositoryImpl implements OrderRepository{

    private final BookDataSource bookDataSource;

    public OrderrepositoryImpl(BookDataSource bookDataSource) {
        this.bookDataSource = bookDataSource;
    }

    @Override
    public void addOrder(Order order) {
        System.out.println("Заказ добавлен!");
        bookDataSource.getOrders().add(order);
    }

    @Override
    public void closeOrder(Order order) {
        System.out.println("Заказ закрыт!");
        bookDataSource.getOrders().get(bookDataSource.getOrders().indexOf(order)).setOrderStatusCompleate();
    }

    @Override
    public void cancelOrder(Order order) {
        System.out.println("Заказ отменён!");
        bookDataSource.getOrders().get(bookDataSource.getOrders().indexOf(order)).setOrderStatusCanceled();
    }

    @Override
    public void printOrderRepository() {
        System.out.println("Список всех заказов:");
        bookDataSource.getOrders().print();
    }
}
