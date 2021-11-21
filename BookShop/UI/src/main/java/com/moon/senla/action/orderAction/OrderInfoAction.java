package com.moon.senla.action.orderAction;

import com.moon.senla.CustomException;
import com.moon.senla.action.AbstractAction;
import com.moon.senla.action.Manager;
import com.moon.senla.entity.Order;
import org.springframework.stereotype.Component;

@Component
public class OrderInfoAction extends AbstractAction {

  public OrderInfoAction(Manager manager) {
    super(manager);
  }

  @Override
  public void execute() {
    System.out.println("Enter ID of the order you want to find out information about");
    int id;
    boolean isValid = true;
    while (isValid) {
      try {
        id = customScanner.getInt();
        check.checkOrder(id);
        Order order = manager.getOrderService().findOrderById(id);
        printOut.printEntity(order);
        isValid = false;
      } catch (CustomException e) {
        System.out.println(" Value is incorrect");
      }
    }
  }
}
