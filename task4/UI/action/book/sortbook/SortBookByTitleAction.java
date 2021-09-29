package task4.UI.action.book.sortbook;

import task4.UI.action.AbstractAction;
import task4.UI.action.IAction;

public class SortBookByTitleAction extends AbstractAction implements IAction {

  @Override
  public void execute() {
    manager.getBookService().sortBookByTitle();
  }
}
