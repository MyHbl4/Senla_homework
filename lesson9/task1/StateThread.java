package lesson9.task1;

import static java.lang.Thread.sleep;

public class StateThread {
  private final String name = "State thread";

  public static void main(String[] args) {
    FirstThread ft = new FirstThread();
    StateThread state = new StateThread();
    try {
      state.print(ft);/////////////////////////////////////new

      ft.start();
      state.print(ft);/////////////////////////////////////runnable

      sleep(500);
      state.print(ft);/////////////////////////////////////time-waiting

      sleep(3000);
      state.print(ft);/////////////////////////////////////waiting
      synchronized (FirstThread.getObject()) {
        FirstThread.sleep(1000);
        FirstThread.getObject().notify();
        state.print(ft);////////////////////////////////////blocked
      }
      synchronized (FirstThread.getObject()) {
        FirstThread.sleep(1000);
        FirstThread.getObject().notify();
      }
      sleep(2000);
      state.print(ft);////////////////////////////////////terminated

    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  public void print(Thread myThread) {
    System.out.println(getName() + ": " + myThread.getState());
  }

  public String getName() {
    return name;
  }
}
