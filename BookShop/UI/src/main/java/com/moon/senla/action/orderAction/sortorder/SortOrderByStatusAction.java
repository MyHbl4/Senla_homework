package com.moon.senla.action.orderAction.sortorder;

import java.util.List;

import com.moon.senla.action.AbstractAction;
import com.moon.senla.action.Manager;
import com.moon.senla.entity.Order;

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
