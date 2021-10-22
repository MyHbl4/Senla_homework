package lesson9.task1;

public class FirstThread extends Thread {

  private static final Object object = new Object();

  public static Object getObject() {
    return object;
  }

  @Override
  public void run() {
//    try {
//      Thread.sleep(1000);
//    } catch (Exception e) {
//      e.printStackTrace();
//    }
    try {
      Thread.sleep(1000);
//      Thread.sleep(1000);
      synchronized (object) {
        object.wait();
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}