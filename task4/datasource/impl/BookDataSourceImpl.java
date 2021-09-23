package task4.datasource.impl;

import java.util.ArrayList;
import java.util.List;
import task4.datasource.BookDataSource;
import task4.model.Book;

public class BookDataSourceImpl implements BookDataSource {
  private final List<Book> books = new ArrayList<>();

  public BookDataSourceImpl() {
    initData();
  }

  public void initData() {

    books.add(new Book("Война и мир", "Лев Толстой", 150, 1867));
    books.add(new Book("Ромео и Джульетта", "Уильям Шекспир", 50, 1597));
    books.add(new Book("Десять негритят", "Агата Кристи", 70, 1939));
    books.add(new Book("Java руководство для начинающих", "Герберт Шилдт", 150, 2002));
    books.add(new Book("Приключения Тома Сойера", "Марк Твен", 40, 1876));
    books.add(new Book("Хоббит, или Туда и Обратно", "Джон Толкин", 120, 1937));
    books.add(new Book("Гарри Поттер и филосовский камень", "Джоан Роулинг", 100, 2016));
    books.add(new Book("Маленький принц", "Антуана де Сент-Экзюпери", 50, 1943));
    books.add(new Book("Властелин колец", "Джон Толкин", 70, 1954));
    books.add(new Book("Повесть о двух городах", "Чарльз Дикенс", 80, 1859));
  }

  @Override
  public List<Book> getBooks() {
    return books;
  }
}
