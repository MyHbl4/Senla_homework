package com.moon.senla.action.orderAction.sortorder;

import java.util.List;

import com.moon.senla.action.AbstractAction;
import com.moon.senla.action.Manager;
import com.moon.senla.entity.Order;

public class SortCompletedOrderByExecutionDateAction extends AbstractAction {

  public SortCompletedOrderByExecutionDateAction(Manager manager) {
    super(manager);
  }

  @Override
  public void execute() {
    System.out.println("Enter for how many months, show the list");
    int months;
    months = customScanner.getInt();
    List<Order> sortOrders = manager.getOrderService().sortCompletedOrderByExecutionDate(months);
    printOut.printList(sortOrders);
  }
}
