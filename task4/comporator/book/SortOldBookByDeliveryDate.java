package task4.comporator.book;

import java.util.Comparator;
import task4.model.Book;

public class SortOldBookByDeliveryDate implements Comparator<Book> {

  @Override
  public int compare(Book o1, Book o2) {

    if (o1.getDeliveryDate().isBefore(o2.getDeliveryDate())) {
      return -1;
    } else if (o1.getDeliveryDate().isAfter(o2.getDeliveryDate())) {
      return 1;
    } else {
      return 0;
    }
  }
}
