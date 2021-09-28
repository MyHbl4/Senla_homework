package task4.UI.action.book.sortbook;

import task4.UI.action.CreateManager;
import task4.UI.action.IAction;

public class SortOldBookByDate extends CreateManager implements IAction {

  @Override
  public void execute() {
    manager.sortOldBookByDeliveryDate();
  }
}
