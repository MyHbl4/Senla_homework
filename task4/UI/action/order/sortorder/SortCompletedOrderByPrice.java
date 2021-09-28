package task4.UI.action.order.sortorder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import task4.UI.action.CreateManager;
import task4.UI.action.IAction;
import task4.exception.CustomScanner;

public class SortCompletedOrderByPrice extends CreateManager implements IAction {
  private CustomScanner customScanner;

  public SortCompletedOrderByPrice(CustomScanner customScanner) {
    this.customScanner = customScanner;
  }

  @Override
  public void execute() {
    System.out.println("Enter for how many months, show the list");
    int months;
    months = customScanner.getInt();
    manager.sortCompletedOrderByPrice(months);
  }
}
