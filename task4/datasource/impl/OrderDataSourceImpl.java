package task4.datasource.impl;

import java.util.ArrayList;
import java.util.List;
import task4.datasource.OrderDataSource;
import task4.model.Book;
import task4.model.Order;

public class OrderDataSourceImpl implements OrderDataSource {
  private final List<Order> orders = new ArrayList<>();

  public OrderDataSourceImpl() {
    initData();
  }

  public void initData() {

    orders.add(
        new Order(
            "Василий Сюткин",
            new ArrayList<>() {
              {
                add(new Book("Хоббит, или Туда и Обратно", "Джон Толкин", 120, 1937));
                add(new Book("Война и мир", "Лев Толстой", 150, 1867));
              }
            }));
    orders.add(
        new Order(
            "Иван Царевич",
            new ArrayList<>() {
              {
                add(new Book("Ромео и Джульетта", "Уильям Шекспир", 50, 1597));
                add(new Book("Десять негритят", "Агата Кристи", 70, 1939));
              }
            }));
    orders.add(
        new Order(
            "Александр Великий",
            new ArrayList<>() {
              {
                add(new Book("Java руководство для начинающих", "Герберт Шилдт", 150, 2002));
              }
            }));
  }

  @Override
  public List<Order> getOrders() {
    return orders;
  }
}
