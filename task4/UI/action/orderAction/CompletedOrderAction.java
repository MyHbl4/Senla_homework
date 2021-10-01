package task4.UI.action.orderAction;

import task4.UI.action.AbstractAction;
import task4.UI.action.IAction;
import task4.model.Order;
import task4.util.CustomScanner;

public class CompletedOrderAction extends AbstractAction {

  @Override
  public void execute() {
    System.out.println("Enter for how many months, display the number of completed orders");
    int months;
    months = customScanner.getInt();
    int count = 0;
    for (Order order : manager.getOrderService().getCompletedOrderList(months)) {
      count++;
    }
    System.out.println("In " + months + " months, " + count + " orders were completed");
  }
}
