package task4.UI.action;

import task4.util.CheckOfData;
import task4.util.CustomScanner;
import task4.util.PrintOut;

public abstract class AbstractAction implements IAction {
  public Manager manager;
  public PrintOut printOut;
  public CustomScanner customScanner;
  public CheckOfData check;

  public AbstractAction(Manager manager) {
    this.manager = manager;
    this.customScanner = new CustomScanner();
    this.printOut = new PrintOut();
    this.check = new CheckOfData(manager);
  }
}
