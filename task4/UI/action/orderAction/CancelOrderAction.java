package task4.UI.action.orderAction;

import task4.UI.action.AbstractAction;
import task4.UI.action.IAction;
import task4.exception.CheckOfData;
import task4.exception.CustomException;
import task4.exception.CustomScanner;

public class CancelOrderAction extends AbstractAction implements IAction {
  private CheckOfData checkOfData;
  private CustomScanner customScanner;

  public CancelOrderAction(CheckOfData checkData, CustomScanner customScanner) {
    this.checkOfData = checkData;
    this.customScanner = customScanner;
  }

  @Override
  public void execute() {
    System.out.println("Enter ID of the order you want to cancel");
    int id;
    boolean isValid = true;
    while (isValid) {
      try {
        id = customScanner.getInt();
        checkOfData.checkOrder(id);
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
