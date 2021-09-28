package task4.UI.action.book;

import task4.UI.action.CreateManager;
import task4.UI.action.IAction;
import task4.exception.CheckOfData;
import task4.exception.CustomException;
import task4.exception.CustomScanner;

public class RemoveBook extends CreateManager implements IAction {
  private CheckOfData checkOfData;
  private CustomScanner customScanner;

  public RemoveBook(CheckOfData checkOfData, CustomScanner customScanner) {
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
        manager.removeBook(id);
        manager.updateBookCsv();
        System.out.println("The book has been removed");
        isValid = false;
      } catch (CustomException e) {
        System.out.println(" Value is incorrect");
      }
    }
  }
}
