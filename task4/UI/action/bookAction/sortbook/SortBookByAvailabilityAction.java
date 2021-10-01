package task4.UI.action.bookAction.sortbook;

import java.util.List;
import task4.UI.action.AbstractAction;
import task4.model.Book;

public class SortBookByAvailabilityAction extends AbstractAction {

  @Override
  public void execute() {
    List<Book> sortBooks = manager.getBookService().sortBookByAvailability();
    printOut.printBook(sortBooks);
  }
}
