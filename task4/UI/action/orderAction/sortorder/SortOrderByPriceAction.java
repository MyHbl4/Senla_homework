package task4.UI.action.orderAction.sortorder;

import task4.UI.action.AbstractAction;
import task4.UI.action.IAction;

public class SortOrderByPriceAction extends AbstractAction implements IAction {

  @Override
  public void execute() {
    manager.getOrderService().sortOrderByPrice();
  }
}
