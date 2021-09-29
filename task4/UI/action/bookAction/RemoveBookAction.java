package task4.UI.action.bookAction;

import task4.UI.action.AbstractAction;
import task4.UI.action.IAction;
import task4.exception.CheckOfData;
import task4.exception.CustomException;
import task4.exception.CustomScanner;

public class RemoveBookAction extends AbstractAction implements IAction {
  private CheckOfData checkOfData;
  private CustomScanner customScanner;

  public RemoveBookAction(CheckOfData checkOfData, CustomScanner customScanner) {
    this.checkOfData = checkOfData;
    this.customScanner = customScanner;
  }

  @Override
  public void execute() {
    System.out.println("Enter ID of the book to remove from availability");
    int id;
    boolean isValid = true;
    while (isValid) {
      try {
        id = customScanner.getInt();
        checkOfData.checkBook(id);
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
