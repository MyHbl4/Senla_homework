package lesson9.task1;

public class StateThread {
  private final String name = "State thread";

  public static void main(String[] args) {
    FirstThread ft = new FirstThread();
    SecondThread st = new SecondThread();
    StateThread state = new StateThread();
    try {
      state.print(ft);
      ft.start();
      state.print(ft);
      st.start();
      Thread.sleep(500);
      state.print(ft);
      state.print(st);
      Thread.sleep(3000);
      synchronized (SecondThread.getObject()) {
        SecondThread.getObject().notify();
      }
      state.print(st);
      state.print(ft);
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
