package task4.comporator.request;

import java.util.Comparator;
import task4.model.Request;

public class SortRequestByTitle implements Comparator<Request> {

  @Override
  public int compare(Request o1, Request o2) {
    return o1.getTitle().compareTo(o2.getTitle());
  }
}
