package task4.UI.action.request.sortrequest;

import task4.UI.action.CreateManager;
import task4.UI.action.IAction;

public class SortRequestByCount extends CreateManager implements IAction {

  @Override
  public void execute() {
    manager.sortRequestByCount();
  }
}
