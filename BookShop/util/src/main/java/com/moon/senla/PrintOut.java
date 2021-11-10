package com.moon.senla;

import java.util.List;

public class PrintOut {

  public <T> void printList(List<T> list) {
    list.forEach(System.out::println);
  }

  public <T> void printEntity(T entity) {
    System.out.println(entity);
  }
}
