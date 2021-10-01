package task4.UI.action.orderAction.sortorder;

import java.util.Comparator;
import java.util.List;
import task4.UI.action.AbstractAction;
import task4.UI.action.IAction;
import task4.model.Order;

public class SortOrderByPriceAction extends AbstractAction {

  @Override
  public void execute() {
    List<Order> sortOrders = manager.getOrderService().sortOrderByPrice();
    printOut.printOrder(sortOrders);
  }
}
