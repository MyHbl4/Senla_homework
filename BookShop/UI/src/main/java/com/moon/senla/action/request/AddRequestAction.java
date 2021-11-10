package com.moon.senla.action.request;

import com.moon.senla.action.AbstractAction;
import com.moon.senla.action.Manager;

public class AddRequestAction extends AbstractAction {

  public AddRequestAction(Manager manager) {
    super(manager);
  }

  @Override
  public void execute() {
    System.out.println("Enter com.moon.senla.Book ID");
    long bookId;
    bookId = customScanner.getInt();
    manager.getRequestService().addRequest(bookId);
    manager.getRequestService().writerRequestBd();
    System.out.println("com.moon.senla.Request has been added");
  }
}
