package task4.UI.action.orderAction;

import task4.UI.action.AbstractAction;
import task4.UI.action.IAction;
import task4.exception.CustomScanner;

public class AmountEarnedFundsAction extends AbstractAction implements IAction {
private CustomScanner customScanner;

  public AmountEarnedFundsAction(CustomScanner customScanner) {
    this.customScanner = customScanner;
  }

  @Override
  public void execute() {
    System.out.println("Enter the number of months to display the income");
    int months;
      months = customScanner.getInt();
    manager.getOrderService().getAllPriceOfSoldBooks(months);
  }
}
