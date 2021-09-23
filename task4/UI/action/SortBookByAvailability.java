package task4.UI.action;

public class SortBookByAvailability extends CreateManager implements IAction {

  @Override
  public void execute() {
    manager.sortBookByAvailability();
  }
}
