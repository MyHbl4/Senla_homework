package task4.UI.action.order.sortorder;

import task4.UI.action.CreateManager;
import task4.UI.action.IAction;

public class SortOrderByStatus extends CreateManager implements IAction {

  @Override
  public void execute() {
    manager.sortOrderByStatus();
  }
}
