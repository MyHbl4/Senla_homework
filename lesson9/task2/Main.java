package lesson9.task2;

public class Main {
  public int sum = 0;

  public Main() {}

  public static void main(String[] args) {
    Main mn = new Main();
    First first = new First("Я первый", mn);
    Second second = new Second("Нет, я первый", mn);
  }
}

