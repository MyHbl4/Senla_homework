package task4.UI.action.orderAction.sortorder;

import task4.UI.action.AbstractAction;
import task4.UI.action.IAction;
import task4.exception.CustomScanner;

public class SortCompletedOrderByPriceAction extends AbstractAction implements IAction {
  private CustomScanner customScanner;

  public SortCompletedOrderByPriceAction(CustomScanner customScanner) {
    this.customScanner = customScanner;
  }

  @Override
  public void execute() {
    System.out.println("Enter for how many months, show the list");
    int months;
    months = customScanner.getInt();
    manager.getOrderService().getCompletedOrderList(months).sort(((o1, o2) -> o1.getPrice() - o2.getPrice()));
    manager.getOrderService().getCompletedOrderList(months).stream().forEach(System.out::println);
  }
}
