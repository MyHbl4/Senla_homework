package task4.UI.action.orderAction.sortorder;

import task4.UI.action.AbstractAction;
import task4.UI.action.IAction;

public class SortOrderByStatusAction extends AbstractAction implements IAction {

  @Override
  public void execute() {
    manager
        .getOrderService()
        .getAll()
        .sort(((o1, o2) -> o1.getOrderStatus().compareTo(o2.getOrderStatus())));
    manager.getOrderService().getAll().stream().forEach(System.out::println);
  }
}
