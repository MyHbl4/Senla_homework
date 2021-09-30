package task4.UI.action.request.sortrequest;

import task4.UI.action.AbstractAction;
import task4.UI.action.IAction;

public class SortRequestByCountAction extends AbstractAction implements IAction {

  @Override
  public void execute() {
    manager.getRequestService().getAll().sort((r1, r2) -> r1.getCount() - r2.getCount());
    manager.getRequestService().getAll().stream().forEach(System.out::println);
  }
}
