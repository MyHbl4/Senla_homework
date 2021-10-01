package task4.UI.action.bookAction;

import task4.model.Book;
import task4.util.CustomException;

public class BookInfoAction extends BookAbstractAction {

  @Override
  public void execute() {
    System.out.println("Enter ID of the book you want to find out information about");
    int id;
    boolean isValid = true;
    while (isValid) {
      try {
        id = customScanner.getInt();
        check.checkBook(id);
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
