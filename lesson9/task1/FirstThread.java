package lesson9.task1;

public class FirstThread extends Thread {

  public void run() {
    try {
      Thread.sleep(1000);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
