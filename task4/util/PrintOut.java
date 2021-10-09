package task4.util;

import java.util.List;

public class PrintOut {

  public <T> void printList(List<T> list) {
    list.forEach(System.out::println);
  }

  public <T> void printEntity(T entity) {
    System.out.println(entity);
  }
}
