package task4.UI.action;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import task4.model.Book;

public class AddBook extends CreateManager implements IAction {

  @Override
  public void execute() {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    try {
      System.out.println("Enter the name of the book");
      String title = reader.readLine();
      System.out.println("Enter the author of the book");
      String author = reader.readLine();
      System.out.println("Enter the price of the book");
      int price = Integer.parseInt(reader.readLine());
      System.out.println("Enter the year of publication");
      int publication = Integer.parseInt(reader.readLine());
      manager.addBook(new Book(title, author, price, publication));
    } catch (IOException e) {
      e.printStackTrace();
    }
    manager.updateBookCsv();
    System.out.println("The book has been added");
  }
}
