package task4.UI.action;

public class SortOldBookByPrice extends CreateManager implements IAction {

  @Override
  public void execute() {
    manager.sortOldBookByPrice();
  }
}
