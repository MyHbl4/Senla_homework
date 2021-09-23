package task4.UI.action;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import task4.model.Book;
import task4.model.Order;

public class AddOrder extends CreateManager implements IAction {

  @Override
  public void execute() {
    String customerName = null;
    List<Book> books = new ArrayList<>();
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    try {
      System.out.println("Enter the customer's name");
      customerName = reader.readLine();
      System.out.println("Enter the number of books you want to buy");
      int amount = Integer.parseInt(reader.readLine());
      for (int i = 0; i < amount; i++) {
        System.out.println("Enter ID of the book you want to buy");
        int id = Integer.parseInt(reader.readLine());
        books.add(manager.findBookById(id));
      }
      manager.addOrder(new Order(customerName, books));
    } catch (IOException e) {
      e.printStackTrace();
    }
    System.out.println("Order has been added");
  }
}
