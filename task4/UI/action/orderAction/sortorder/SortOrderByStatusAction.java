package task4.UI.action.orderAction.sortorder;

import java.util.List;
import task4.UI.action.AbstractAction;
import task4.UI.action.Manager;
import task4.model.Order;

public class SortOrderByStatusAction extends AbstractAction {

  public SortOrderByStatusAction(Manager manager) {
    super(manager);
  }

  @Override
  public void execute() {
    List<Order> sortOrders = manager.getOrderService().sortOrderByStatus();
    printOut.printList(sortOrders);
  }
}
