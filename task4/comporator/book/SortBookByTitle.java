package task4.comporator.book;

import java.util.Comparator;
import task4.model.Book;

public class SortBookByTitle implements Comparator<Book> {

  @Override
  public int compare(Book o1, Book o2) {
    return o1.getTitle().compareTo(o2.getTitle());
  }
}
