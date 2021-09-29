package task4.UI.action.request.sortrequest;

import task4.UI.action.AbstractAction;
import task4.UI.action.IAction;

public class SortRequestByCountAction extends AbstractAction implements IAction {

  @Override
  public void execute() {
    manager.getRequestService().sortRequestByCount();
  }
}
