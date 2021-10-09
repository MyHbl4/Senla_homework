package task4.UI.action.orderAction;

import task4.UI.action.AbstractAction;
import task4.UI.action.Manager;
import task4.util.CustomException;

public class CloseOrderAction extends AbstractAction {

  public CloseOrderAction(Manager manager) {
    super(manager);
  }

  @Override
  public void execute() {
    System.out.println("Enter ID of the order you want to close");
    int id;
    boolean isValid = true;
    while (isValid) {
      try {
        id = customScanner.getInt();
        check.checkOrder(id);
        manager.getOrderService().closeOrder(id);
        manager.getOrderService().updateOrderCsv();
        System.out.println("The order has been closed");
        isValid = false;
      } catch (CustomException e) {
        System.out.println(" Value is incorrect");
      }
    }
  }
}
