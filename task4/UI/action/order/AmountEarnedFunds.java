package task4.UI.action.order;

import task4.UI.action.CreateManager;
import task4.UI.action.IAction;
import task4.exception.CustomScanner;

public class AmountEarnedFunds extends CreateManager implements IAction {
private CustomScanner customScanner;

  public AmountEarnedFunds(CustomScanner customScanner) {
    this.customScanner = customScanner;
  }

  @Override
  public void execute() {
    System.out.println("Enter the number of months to display the income");
    int months;
      months = customScanner.getInt();
    manager.getAllPriceOfSoldBooks(months);
  }
}
