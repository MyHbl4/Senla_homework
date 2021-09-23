package task4.UI.action;

public class SortBookByPrice extends CreateManager implements IAction {

  @Override
  public void execute() {
    manager.sortBookByPrice();
  }
}
