package com.moon.senla.action;

import com.moon.senla.action.util.CheckOfData;
import com.moon.senla.action.util.CustomScanner;
import com.moon.senla.action.util.PrintOut;
import org.springframework.stereotype.Component;

@Component
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
