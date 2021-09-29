package task4.UI.action.bookAction;

import task4.UI.action.AbstractAction;
import task4.UI.action.IAction;
import task4.exception.CheckOfData;
import task4.exception.CustomException;
import task4.exception.CustomScanner;
import task4.model.Book;

public class BookInfoAction extends AbstractAction implements IAction {
  private CheckOfData checkOfData;
  private CustomScanner customScanner;

  public BookInfoAction(CheckOfData checkOfData, CustomScanner customScanner) {
    this.checkOfData = checkOfData;
    this.customScanner = customScanner;
  }

  @Override
  public void execute() {
    System.out.println("Enter ID of the book you want to find out information about");
    int id;
    boolean isValid = true;
    while (isValid) {
      try {
        id = customScanner.getInt();
        checkOfData.checkBook(id);
        Book book = manager.getBookService().findBookById(id);
        System.out.println(
            "Book ID: "
                + id
                + ", Title: '"
                + book.getTitle()
                + "', Author: '"
                + book.getAuthor()
                + "', Price: "
                + book.getPrice()
                + ", Availability: "
                + book.getAvailability());
        isValid = false;
      } catch (CustomException e) {
        System.out.println(" Value is incorrect");
      }
    }
  }
}
