package task4.UI.action.bookAction;

import task4.UI.action.IAction;
import task4.UI.action.Manager;
import task4.util.Check;
import task4.util.CustomScanner;

public abstract class BookAbstractAction implements IAction {
  public Manager manager = new Manager();
  public CustomScanner customScanner = new CustomScanner();
  public Check check = new Check();
}
