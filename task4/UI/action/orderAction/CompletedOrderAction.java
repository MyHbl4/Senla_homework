package task4.UI.action.orderAction;

import task4.UI.action.AbstractAction;
import task4.UI.action.IAction;
import task4.exception.CustomScanner;

public class CompletedOrderAction extends AbstractAction implements IAction {
  private CustomScanner customScanner;

  public CompletedOrderAction(CustomScanner customScanner) {
    this.customScanner = customScanner;
  }

  @Override
  public void execute() {
    System.out.println("Enter for how many months, display the number of completed orders");
    int months;
    int completedOrders;
    months = customScanner.getInt();
    completedOrders = manager.getOrderService().getCompletedOrder(months);
    System.out.println(
        "In "
            + months
            + " months, "
            + completedOrders
            + " orders were completed");
  }
}
