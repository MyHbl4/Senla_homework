package task4.UI.action.request;

import task4.UI.action.AbstractAction;
import task4.UI.action.Manager;

public class AddRequestAction extends AbstractAction {

  public AddRequestAction(Manager manager) {
    super(manager);
  }

  @Override
  public void execute() {
    System.out.println("Enter Book ID");
    long bookId;
    bookId = customScanner.getInt();
    manager.getRequestService().addRequest(bookId);
    manager.getRequestService().updateRequestCsv();
    System.out.println("Request has been added");
  }
}
