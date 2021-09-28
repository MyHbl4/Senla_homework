package task4.UI.action.book;

import java.util.Scanner;
import task4.UI.action.CreateManager;
import task4.UI.action.IAction;
import task4.exception.CustomScanner;
import task4.model.Book;

public class AddBook extends CreateManager implements IAction {
  private CustomScanner customScanner;

  public AddBook(CustomScanner customScanner) {
    this.customScanner = customScanner;
  }

  @Override
  public void execute() {
    Scanner sc = new Scanner(System.in);
    System.out.println("Enter the name of the book");

    String title = customScanner.getString();
    System.out.println("Enter the author of the book");

    String author = customScanner.getString();
    System.out.println("Enter the price of the book");
    int price = customScanner.getInt();
    System.out.println("Enter the year of publication");
    int publication = customScanner.getInt();
    manager.addBook(new Book(title, author, price, publication));
    manager.updateBookCsv();
    System.out.println("The book has been added");
  }
}
