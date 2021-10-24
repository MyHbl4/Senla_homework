package lesson9.task4;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class TimeThread extends Thread {
  int second;

  public TimeThread(int second) {
    this.second = second;
  }

  @Override
  public void run() {
    while (true) {
      try {
        Thread.sleep(1000 * second);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      System.out.println(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
    }
  }
}
