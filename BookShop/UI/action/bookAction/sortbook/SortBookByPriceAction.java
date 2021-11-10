package task4.UI.action.bookAction.sortbook;

import java.util.List;
import task4.UI.action.AbstractAction;
import task4.UI.action.Manager;
import task4.model.Book;

public class SortBookByPriceAction extends AbstractAction {

  public SortBookByPriceAction(Manager manager) {
    super(manager);
  }

  @Override
  public void execute() {
    List<Book> sortBooks = manager.getBookService().sortBookByPrice();
    printOut.printList(sortBooks);
  }
}
