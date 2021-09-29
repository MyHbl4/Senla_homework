package task4.UI.action.requestAction;

import task4.UI.action.AbstractAction;
import task4.UI.action.IAction;
import task4.exception.CustomScanner;

public class AddRequestAction extends AbstractAction implements IAction {
  private CustomScanner customScanner;

  public AddRequestAction(CustomScanner customScanner) {
    this.customScanner = customScanner;
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
