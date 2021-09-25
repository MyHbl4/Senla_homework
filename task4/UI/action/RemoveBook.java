package task4.UI.action;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class RemoveBook extends CreateManager implements IAction {

  @Override
  public void execute() {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    System.out.println("Enter ID of the book to remove from availability");
    int id = 0;
    try {
      id = Integer.parseInt(reader.readLine());
    } catch (IOException e) {
      e.printStackTrace();
    }
    manager.removeBook(id);
    manager.updateBookCsv();
    System.out.println("The book has been removed");
  }
}
