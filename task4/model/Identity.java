package task4.model;

public abstract class Identity {

  private static Long bookId = 1L;
  private static Long orderid = 1L;

  public static Long createBookId() {
    return bookId++;
  }

  public static Long createOrderid() {
    return orderid++;
  }
}
