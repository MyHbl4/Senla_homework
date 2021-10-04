package task4.UI.action.bookAction;

import task4.UI.action.AbstractAction;
import task4.UI.action.Manager;
import task4.model.Book;

public class AddBookAction extends AbstractAction {

  public AddBookAction(Manager manager) {
    super(manager);
  }

  @Override
  public void execute() {
    System.out.println("Enter the name of the book");
    String title = customScanner.getString();
    System.out.println("Enter the author of the book");
    String author = customScanner.getString();
    System.out.println("Enter the price of the book");
    int price = customScanner.getInt();
    System.out.println("Enter the year of publication");
    int publication = customScanner.getInt();
    manager.getBookService().addBook(new Book(title, author, price, publication));
    manager.getBookService().updateBookCsv();
    manager.getRequestService().updateRequestCsv();
    manager.getOrderService().updateOrderCsv();
    System.out.println("The book has been added");
  }
}
