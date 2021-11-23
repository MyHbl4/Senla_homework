package com.moon.senla.action.orderAction;

import com.moon.senla.action.AbstractAction;
import com.moon.senla.action.Manager;
import com.moon.senla.entity.Order;
import org.springframework.stereotype.Component;

@Component
public class CompletedOrderAction extends AbstractAction {

  public CompletedOrderAction(Manager manager) {
    super(manager);
  }

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
