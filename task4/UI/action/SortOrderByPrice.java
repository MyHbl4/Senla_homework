package task4.UI.action;

public class SortOrderByPrice extends CreateManager implements IAction {

  @Override
  public void execute() {
    manager.sortOrderByPrice();
  }
}
