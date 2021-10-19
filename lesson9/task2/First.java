package lesson9.task2;

class First extends Thread {

  String name;
  Main mn;

  public First(String name, Main mn) {
    this.name = name;
    this.mn = mn;
    this.start();
  }

  public void run() {
    while (true) {
      synchronized (this) {
        if (mn.sum % 2 == 0) {
          System.out.println(Thread.currentThread().getName() + " " + name);
          mn.sum++;
          try {
            Thread.sleep(50);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        } else {
          First.yield();
        }
      }
    }
  }
}
