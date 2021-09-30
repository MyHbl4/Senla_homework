package task4.UI.action.request.sortrequest;

import task4.UI.action.AbstractAction;
import task4.UI.action.IAction;

public class SortRequestByTitleAction extends AbstractAction implements IAction {

  @Override
  public void execute() {
    manager.getRequestService().getAll().sort((r1, r2) -> r1.getTitle().compareTo(r2.getTitle()));
    manager.getRequestService().getAll().stream().forEach(System.out::println);
  }
}
