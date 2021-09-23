package task4.UI.action;

public class SortBookByTitle extends CreateManager implements IAction {

  @Override
  public void execute() {
    manager.sortBookByTitle();
  }
}
