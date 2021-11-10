package com.moon.senla.action.orderAction;

import com.moon.senla.action.AbstractAction;
import com.moon.senla.action.Manager;
import com.moon.senla.CustomException;

public class CancelOrderAction extends AbstractAction {

  public CancelOrderAction(Manager manager) {
    super(manager);
  }

  @Override
  public void execute() {
    System.out.println("Enter ID of the order you want to cancel");
    int id;
    boolean isValid = true;
    while (isValid) {
      try {
        id = customScanner.getInt();
        check.checkOrder(id);
        manager.getOrderService().cancelOrder(id);
        manager.getOrderService().writeOrderBd();
        System.out.println("The order has been canceled");
        isValid = false;
      } catch (CustomException e) {
        System.out.println(" Value is incorrect");
      }
    }
  }
}
