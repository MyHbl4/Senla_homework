package lesson9.task3;

public class Producer extends Thread{
  Factory factory;

  public Producer(Factory factory) {
    this.factory = factory;
    this.start();
  }

  @Override
  public void run() {
    while(true) {
      factory.putNumber();
    }
  }
}
