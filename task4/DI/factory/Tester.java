package task4.DI.factory;

import task4.DI.annotations.InjectProperty;

public class Tester implements Test {
  @InjectProperty("MONTHS_STALE_BOOKS")
  private String months;
  @InjectProperty("FUNCTION_ORDER")
  private String function;

  @Override
  public void recommend() {
    System.out.println("Количество месяцев: " + months);
    System.out.println("Функция в режиме: " + function);
  }
}
