package task4.UI.action.orderAction;

import task4.UI.action.AbstractAction;
import task4.UI.action.Manager;
import task4.exceptions.CustomException;

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
        manager.getOrderService().updateOrderCsv();
        System.out.println("The order has been canceled");
        isValid = false;
      } catch (CustomException e) {
        System.out.println(" Value is incorrect");
      }
    }
  }
}
