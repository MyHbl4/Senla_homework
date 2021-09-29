package task4.UI.action.order;

import task4.UI.action.AbstractAction;
import task4.UI.action.IAction;
import task4.exception.CheckOfData;
import task4.exception.CustomException;
import task4.exception.CustomScanner;

public class CloseOrderAction extends AbstractAction implements IAction {
private CheckOfData checkOfData;
private CustomScanner customScanner;

  public CloseOrderAction(CheckOfData checkOfData, CustomScanner customScanner) {
    this.checkOfData = checkOfData;
    this.customScanner = customScanner;
  }

  @Override
  public void execute() {
    System.out.println("Enter ID of the order you want to close");
    int id;
    boolean isValid = true;
    while (isValid) {
      try {
        id = customScanner.getInt();
        checkOfData.checkOrder(id);
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
