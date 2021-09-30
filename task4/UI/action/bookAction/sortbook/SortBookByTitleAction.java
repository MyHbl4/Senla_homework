package task4.UI.action.bookAction.sortbook;

import task4.UI.action.AbstractAction;
import task4.UI.action.IAction;

public class SortBookByTitleAction extends AbstractAction implements IAction {

  @Override
  public void execute() {
    manager.getBookService().getAll().sort((b1, b2) -> b1.getTitle().compareTo(b2.getTitle()));
    manager.getBookService().getAll().stream().forEach(System.out::println);
  }
}
