package task4.UI.action;

import task4.util.CheckOfData;
import task4.util.CustomScanner;
import task4.util.PrintOut;

public abstract class AbstractAction implements IAction {
  public Manager manager = new Manager();
  public PrintOut printOut = new PrintOut();
  public CustomScanner customScanner = new CustomScanner();
  public CheckOfData check = new CheckOfData();
}
