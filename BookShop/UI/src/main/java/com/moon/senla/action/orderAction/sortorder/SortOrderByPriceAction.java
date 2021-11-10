package com.moon.senla.action.orderAction.sortorder;

import java.util.List;
import com.moon.senla.action.AbstractAction;
import com.moon.senla.action.Manager;
import com.moon.senla.Order;

public class SortOrderByPriceAction extends AbstractAction {

  public SortOrderByPriceAction(Manager manager) {
    super(manager);
  }

  @Override
  public void execute() {
    List<Order> sortOrders = manager.getOrderService().sortOrderByPrice();
    printOut.printList(sortOrders);
  }
}
