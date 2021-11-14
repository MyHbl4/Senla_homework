package com.moon.senla.action.bookAction.sortbook;

import java.util.List;
import com.moon.senla.action.AbstractAction;
import com.moon.senla.action.Manager;
import com.moon.senla.entity.Book;

public class SortOldBookByDateAction extends AbstractAction {

  public SortOldBookByDateAction(Manager manager) {
    super(manager);
  }

  @Override
  public void execute() {
    List<Book> sortBooks = manager.getBookService().sortOldBookByDate();
    printOut.printList(sortBooks);
  }
}
