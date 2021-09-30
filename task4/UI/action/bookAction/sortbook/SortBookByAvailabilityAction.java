package task4.UI.action.bookAction.sortbook;

import task4.UI.action.AbstractAction;
import task4.UI.action.IAction;

public class SortBookByAvailabilityAction extends AbstractAction implements IAction {

  @Override
  public void execute() {
    manager.getBookService().getAll().sort((b1, b2) -> b1.getAvailability().compareTo(b2.getAvailability()));
    manager.getBookService().getAll().stream().forEach(System.out::println);
  }
}
