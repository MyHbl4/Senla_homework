package task4.UI.action;

public class SortRequestByTitle extends CreateManager implements IAction {

  @Override
  public void execute() {
    manager.sortRequestByTitle();
  }
}
