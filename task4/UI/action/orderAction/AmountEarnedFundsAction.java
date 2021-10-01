package task4.UI.action.orderAction;

import task4.UI.action.AbstractAction;
import task4.UI.action.IAction;
import task4.util.CustomScanner;

public class AmountEarnedFundsAction extends AbstractAction implements IAction {
  private CustomScanner customScanner;

  public AmountEarnedFundsAction(CustomScanner customScanner) {
    this.customScanner = customScanner;
  }

  @Override
  public void execute() {
    System.out.println("Enter the number of months to display the income");
    int months;
    int price;
    months = customScanner.getInt();
    price = manager.getOrderService().getAllPriceOfSoldBooks(months);
    System.out.println("Revenue for " + months + " months amounted to: " + price);
  }
}
