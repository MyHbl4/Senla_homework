package com.moon.senla.action.request;

import com.moon.senla.action.AbstractAction;
import com.moon.senla.action.Manager;

public class AddRequestAction extends AbstractAction {

  public AddRequestAction(Manager manager) {
    super(manager);
  }

  @Override
  public void execute() {
    System.out.println("Enter the book ID");
    long bookId;
    bookId = customScanner.getInt();
    manager.getRequestService().addRequest(bookId);
    System.out.println("Request has been added");
  }
}
