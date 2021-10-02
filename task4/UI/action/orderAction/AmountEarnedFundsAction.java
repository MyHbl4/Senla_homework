package task4.UI.action.orderAction;

import task4.UI.action.AbstractAction;
import task4.UI.action.Manager;

public class AmountEarnedFundsAction extends AbstractAction {

  public AmountEarnedFundsAction(Manager manager) {
    super(manager);
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
