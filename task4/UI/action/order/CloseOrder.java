package task4.UI.action.order;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import task4.UI.action.CreateManager;
import task4.UI.action.IAction;
import task4.exception.CheckOfData;
import task4.exception.CustomException;
import task4.exception.CustomScanner;

public class CloseOrder extends CreateManager implements IAction {
private CheckOfData checkOfData;
private CustomScanner customScanner;

  public CloseOrder(CheckOfData checkOfData, CustomScanner customScanner) {
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
        manager.closeOrder(id);
        manager.updateOrderCsv();
        System.out.println("The order has been closed");
        isValid = false;
      } catch (CustomException e) {
        System.out.println(" Value is incorrect");
      }
    }
  }
}
