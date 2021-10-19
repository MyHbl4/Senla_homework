package lesson9.task3;

public class Store {
  public static void main(String[] args) {
    Factory factory = new Factory();
    Producer producer = new Producer(factory);
    Customer consumer = new Customer(factory);

  }
}
