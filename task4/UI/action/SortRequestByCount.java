package task4.UI.action;

public class SortRequestByCount extends CreateManager implements IAction {

  @Override
  public void execute() {
    manager.sortRequestByCount();
  }
}
