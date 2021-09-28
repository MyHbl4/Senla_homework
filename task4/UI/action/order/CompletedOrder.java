package task4.UI.action.order;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import task4.UI.action.CreateManager;
import task4.UI.action.IAction;
import task4.exception.CustomScanner;

public class CompletedOrder extends CreateManager implements IAction {
  private CustomScanner customScanner;

  public CompletedOrder(CustomScanner customScanner) {
    this.customScanner = customScanner;
  }

  @Override
  public void execute() {
    System.out.println("Enter for how many months, display the number of completed orders");
    int months;
    months = customScanner.getInt();
    manager.getCompletedOrder(months);
  }
}
