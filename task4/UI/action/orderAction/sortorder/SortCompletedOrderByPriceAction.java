package task4.UI.action.orderAction.sortorder;

import java.util.List;
import task4.UI.action.AbstractAction;
import task4.UI.action.Manager;
import task4.model.Order;

public class SortCompletedOrderByPriceAction extends AbstractAction {

  public SortCompletedOrderByPriceAction(Manager manager) {
    super(manager);
  }

  @Override
  public void execute() {
    System.out.println("Enter for how many months, show the list");
    int months;
    months = customScanner.getInt();
    List<Order> sortOrders = manager.getOrderService().sortCompletedOrderByPrice(months);
    printOut.printList(sortOrders);
  }
}
