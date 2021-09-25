package task4.UI.action;

public class SortOrderByStatus extends CreateManager implements IAction {

  @Override
  public void execute() {
    manager.sortOrderByStatus();
  }
}
