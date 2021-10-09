package task4.UI.action.bookAction.sortbook;

import java.util.List;
import task4.UI.action.AbstractAction;
import task4.UI.action.Manager;
import task4.model.Book;

public class SortOldBookByPriceAction extends AbstractAction {

  public SortOldBookByPriceAction(Manager manager) {
    super(manager);
  }

  @Override
  public void execute() {
    List<Book> sortBooks = manager.getBookService().sortOldBookByPrice();
    printOut.printList(sortBooks);
  }
}
