package task4.UI.action.orderAction.sortorder;

import java.util.List;
import task4.UI.action.AbstractAction;
import task4.UI.action.IAction;
import task4.model.Order;

public class SortOrderByExecutionDateAction extends AbstractAction {

  @Override
  public void execute() {
    List<Order> sortOrders = manager.getOrderService().sortOrderByExecutionDate();
    printOut.printOrder(sortOrders);
  }
}
