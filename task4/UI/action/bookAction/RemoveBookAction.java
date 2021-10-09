package task4.UI.action.bookAction;

import task4.UI.action.AbstractAction;
import task4.UI.action.Manager;
import task4.util.CustomException;

public class RemoveBookAction extends AbstractAction {

  public RemoveBookAction(Manager manager) {
    super(manager);
  }

  @Override
  public void execute() {
    System.out.println("Enter ID of the book to remove from availability");
    int id;
    boolean isValid = true;
    while (isValid) {
      try {
        id = customScanner.getInt();
        check.checkBook(id);
        manager.getBookService().removeBook(id);
        manager.getBookService().updateBookCsv();
        System.out.println("The book has been removed");
        isValid = false;
      } catch (CustomException e) {
        System.out.println(" Value is incorrect");
      }
    }
  }
}
