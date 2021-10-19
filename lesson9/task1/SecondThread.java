package lesson9.task1;

public class SecondThread extends Thread {
  private static final Object object = new Object();

  public static Object getObject() {
    return object;
  }

  @Override
  public void run() {
    try {
      synchronized (object) {
        object.wait();
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
