package task4.comporator.book;

import java.util.Comparator;
import task4.model.Book;

public class SortBookByPrice implements Comparator<Book> {

  @Override
  public int compare(Book o1, Book o2) {
    return o1.getPrice() - o2.getPrice();
  }
}
