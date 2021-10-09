package task4.UI.action.request;

import task4.UI.action.AbstractAction;
import task4.UI.action.Manager;

public class AddRequestAction extends AbstractAction {

  public AddRequestAction(Manager manager) {
    super(manager);
  }

  @Override
  public void execute() {
    System.out.println("Enter the name of the book");
    String request;
    request = customScanner.getString();
    manager.getOrderService().addRequest(request);
    manager.getRequestService().updateRequestCsv();
    System.out.println("Request has been added");
  }
}
