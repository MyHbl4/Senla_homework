package task4.UI.action.orderAction.sortorder;

import task4.UI.action.AbstractAction;
import task4.UI.action.IAction;

public class SortOrderByPriceAction extends AbstractAction implements IAction {

  @Override
  public void execute() {
    manager.getOrderService().getAll().sort(((o1, o2) -> o1.getPrice() - o2.getPrice()));
    manager.getOrderService().getAll().stream().forEach(System.out::println);
  }
}
