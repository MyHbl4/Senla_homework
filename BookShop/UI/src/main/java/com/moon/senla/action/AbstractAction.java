package com.moon.senla.action;

import com.moon.senla.CheckOfData;
import com.moon.senla.CustomScanner;
import com.moon.senla.PrintOut;

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
