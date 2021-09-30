package task4.UI.action.bookAction.sortbook;

import task4.UI.action.AbstractAction;
import task4.UI.action.IAction;

public class SortOldBookByPriceAction extends AbstractAction implements IAction {

  @Override
  public void execute() {
    manager.getBookService().getOldBooks().sort((b1, b2) -> b1.getPrice() - b2.getPrice());
    manager.getBookService().getOldBooks().stream().forEach(System.out::println);
  }
}
