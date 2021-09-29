package task4.UI.action.order.sortorder;

import task4.UI.action.AbstractAction;
import task4.UI.action.IAction;
import task4.exception.CustomScanner;

public class SortCompletedOrderByExecutionDateAction extends AbstractAction implements IAction {
  private CustomScanner customScanner;

  public SortCompletedOrderByExecutionDateAction(CustomScanner customScanner) {
    this.customScanner = customScanner;
  }

  @Override
  public void execute() {
    System.out.println("Enter for how many months, show the list");
    int months;
    months = customScanner.getInt();
    manager.getOrderService().sortCompletedOrderByExecutionDate(months);
  }
}
