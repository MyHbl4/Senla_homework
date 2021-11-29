package com.moon.senla.action.orderAction.sortorder;

import java.util.List;

import com.moon.senla.action.AbstractAction;
import com.moon.senla.action.Manager;
import com.moon.senla.entity.Order;
import org.springframework.stereotype.Component;

@Component
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
