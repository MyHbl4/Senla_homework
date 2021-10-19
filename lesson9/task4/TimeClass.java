package lesson9.task4;

public class TimeClass {
  public static void main(String[] args) {
    TimeThread timeThread = new TimeThread(2);
    timeThread.start();
  }
}
