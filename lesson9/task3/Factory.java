package lesson9.task3;

import static java.lang.Thread.sleep;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Factory {

  private int counter = 0;
  private Random random = new Random();
  private List<Integer> listNumber = new ArrayList<>();

  public synchronized void getNumber() {
    while (counter < 1) {
      try {
        wait();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }

    counter--;
    listNumber.remove(random.nextInt(listNumber.size()));
    System.out.println("Some number is gotten " + "Number of numbers " + counter);
    try {
      sleep(500);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    notify();
  }

  public synchronized void putNumber() {
    while (counter >= 3) {
      try {
        wait();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    int number = random.nextInt();
    listNumber.add(number);
    counter++;
    System.out.println("Number " + number + " is put! " + "Number of numbers " + counter);
    try {
      sleep(500);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    notify();
  }
}
