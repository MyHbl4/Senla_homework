package com.moon.senla.action.bookAction.sortbook;

import java.util.List;

import com.moon.senla.action.AbstractAction;
import com.moon.senla.action.Manager;
import com.moon.senla.entity.Book;

public class SortBookByAvailabilityAction extends AbstractAction {

  public SortBookByAvailabilityAction(Manager manager) {
    super(manager);
  }

  @Override
  public void execute() {
    List<Book> sortBooks = manager.getBookService().sortBookByAvailability();
    printOut.printList(sortBooks);
  }
}
