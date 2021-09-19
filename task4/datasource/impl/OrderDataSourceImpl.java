package task4.datasource.impl;

import task4.datasource.OrderDataSource;
import task4.model.Book;
import task4.model.Genre;
import task4.model.Order;
import task4.util.OrderArrayList;

public class OrderDataSourceImpl implements OrderDataSource {
  private final OrderArrayList orders = new OrderArrayList();

  public OrderDataSourceImpl() {
    initData();
  }

  public void initData() {

    orders.add(
        new Order(
            1,
            2,
            new Object[] {
              new Book(1, "Война и мир", "Лев Толстой", 150, Genre.NOVEL, 1867),
              new Book(
                  7, "Гарри Поттер и филосовский камень", "Джоан Роулинг", 100, Genre.NOVEL, 2016)
            }));
    orders.add(
        new Order(
            2,
            3,
            new Object[] {
              new Book(2, "Ромео и Джульетта", "Уильям Шекспир", 50, Genre.NOVEL, 1597)
            }));
    orders.add(
        new Order(
            3,
            1,
            new Object[] {
              new Book(
                  6, "Хоббит, или Туда и Обратно", "Джон Толкин", 120, Genre.SCIENCE_FICTION, 1937),
              new Book(
                  4,
                  "Java руководство для начинающих",
                  "Герберт Шилдт",
                  150,
                  Genre.EDUKATIONAL_BOOK,
                  2002),
              new Book(9, "Властелин колец", "Джон Толкин", 70, Genre.SCIENCE_FICTION, 1954)
            }));
  }

  @Override
  public OrderArrayList getOrders() {
    return orders;
  }
}
