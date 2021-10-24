package lesson9.task3;

public class Customer extends Thread{
  Factory factory;

  public Customer(Factory factory) {
    this.factory = factory;
    this.start();
  }

  @Override
  public void run() {
    while(true) {
      factory.getNumber();
    }
  }
}
