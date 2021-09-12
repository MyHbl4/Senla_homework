package task4.datasource;

import task4.model.Book;
import task4.model.Genre;
import task4.other.BookArrayList;

public class BookDataSourceImpl implements BookDataSource {
  private final BookArrayList books = new BookArrayList();

  public BookDataSourceImpl() {
    initData();
  }

  public void initData() {

    books.add(new Book(1, "Война и мир", "Лев Толстой", 150, Genre.NOVEL));
    books.add(new Book(2, "Ромео и Джульетта", "Уильям Шекспир", 50, Genre.NOVEL));
    books.add(new Book(3, "Десять негритят", "Агата Кристи", 70, Genre.DETECTIVE));
    books.add(
        new Book(
            4, "Java руководство для начинающих", "Герберт Шилдт", 150, Genre.EDUKATIONAL_BOOK));
    books.add(new Book(5, "Приключения Тома Сойера", "Марк Твен", 40, Genre.NOVEL));
    books.add(new Book(6, "Хоббит, или Туда и Обратно", "Джон Толкин", 120, Genre.SCIENCE_FICTION));
    books.add(new Book(7, "Гарри Поттер и филосовский камень", "Джоан Роулинг", 100, Genre.NOVEL));
    books.add(new Book(8, "Маленький принц", "Антуана де Сент-Экзюпери", 50, Genre.CHILDREN_BOOK));
    books.add(new Book(9, "Властелин колец", "Джон Толкин", 70, Genre.SCIENCE_FICTION));
    books.add(new Book(10, "Повесть о двух городах", "Чарльз Дикенс", 80, Genre.NOVEL));
  }

  @Override
  public BookArrayList getBooks() {
    return books;
  }
}
