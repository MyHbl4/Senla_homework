package task4.datasource.impl;

import task4.datasource.OrderDataSource;
import task4.model.EnumBook;
import task4.model.Order;
import task4.util.OrderArrayList;

public class OrderDataSourceImpl implements OrderDataSource {
  private final OrderArrayList orders = new OrderArrayList();

  public OrderDataSourceImpl() {
    initData();
  }

  public void initData() {

    orders.add(new Order(1, 2, new Object[] {EnumBook.HURRY_POTTER, EnumBook.HOBBIT}));
    orders.add(new Order(2, 3, new Object[] {EnumBook.JAVA_GUIDE}));
    orders.add(new Order(3, 1, new Object[] {EnumBook.LORD_OF_THE_RING}));
  }

  @Override
  public OrderArrayList getOrders() {
    return orders;
  }
}
