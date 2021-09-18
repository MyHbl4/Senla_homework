package task4.model;

public enum EnumBook {
  WAR_AND_PEACE(1, "Война и мир", "Лев Толстой", 150, Genre.NOVEL, 1867),
  ROMEO_AND_JULIET(2, "Ромео и Джульетта", "Уильям Шекспир", 50, Genre.NOVEL, 1597),
  TEN_LITTLE_NEGROS(3, "Десять негритят", "Агата Кристи", 70, Genre.DETECTIVE, 1939),
  JAVA_GUIDE(
      4, "Java руководство для начинающих", "Герберт Шилдт", 150, Genre.EDUKATIONAL_BOOK, 2002),
  TOM_SAWYER(5, "Приключения Тома Сойера", "Марк Твен", 40, Genre.NOVEL, 1876),
  HOBBIT(6, "Хоббит, или Туда и Обратно", "Джон Толкин", 120, Genre.SCIENCE_FICTION, 1937),
  HURRY_POTTER(7, "Гарри Поттер и филосовский камень", "Джоан Роулинг", 100, Genre.NOVEL, 2016),
  LITTLE_PRINCE(8, "Маленький принц", "Антуана де Сент-Экзюпери", 50, Genre.CHILDREN_BOOK, 1943),
  LORD_OF_THE_RING(9, "Властелин колец", "Джон Толкин", 70, Genre.SCIENCE_FICTION, 1954),
  TALE_OF_TWO_CITIES(10, "Повесть о двух городах", "Чарльз Дикенс", 80, Genre.NOVEL, 1859);

  private final int id;
  private final String title;
  private final String author;
  private final int price;
  private final Genre genre;
  private final int publication;

  EnumBook(int id, String title, String author, int price, Genre genre, int publication) {
    this.id = id;
    this.title = title;
    this.author = author;
    this.price = price;
    this.genre = genre;
    this.publication = publication;
  }

  public int getId() {
    return id;
  }

  public String getTitle() {
    return title;
  }

  public String getAuthor() {
    return author;
  }

  public int getPrice() {
    return price;
  }

  public Genre getGenre() {
    return genre;
  }

  public int getPublication() {
    return publication;
  }
}
