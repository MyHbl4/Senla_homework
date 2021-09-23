package task4.UI.action;

public class SortBookByPublication extends CreateManager implements IAction {

  @Override
  public void execute() {
    manager.sortBookByPublishedDate();
  }
}
