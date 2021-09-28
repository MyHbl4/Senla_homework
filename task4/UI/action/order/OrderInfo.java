package task4.UI.action.order;

import java.util.Scanner;
import task4.UI.action.CreateManager;
import task4.UI.action.IAction;
import task4.exception.CheckOfData;
import task4.exception.CustomException;
import task4.exception.CustomScanner;

public class OrderInfo extends CreateManager implements IAction {
  private CheckOfData checkOfData;
  private CustomScanner customScanner;

  public OrderInfo(CheckOfData checkData, CustomScanner customScanner) {
    this.checkOfData = checkData;
    this.customScanner = customScanner;
  }

  @Override
  public void execute() {
    System.out.println("Enter ID of the order you want to find out information about");
    int id;
    boolean isValid = true;
    while (isValid) {
      try {
        id = customScanner.getInt();
        checkOfData.checkOrder(id);
        manager.orderInfoById(id);
        isValid = false;
      } catch (CustomException e) {
        System.out.println(" Value is incorrect");
      }
    }
  }
}
