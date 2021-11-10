package com.moon.senla.action.bookAction.sortbook;

import java.util.List;
import com.moon.senla.action.AbstractAction;
import com.moon.senla.action.Manager;
import com.moon.senla.Book;

public class SortBookByPublicationAction extends AbstractAction {

  public SortBookByPublicationAction(Manager manager) {
    super(manager);
  }

  @Override
  public void execute() {
    List<Book> sortBooks = manager.getBookService().sortBookByPublication();
    printOut.printList(sortBooks);
  }
}
