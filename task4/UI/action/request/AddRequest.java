package task4.UI.action.request;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import task4.UI.action.CreateManager;
import task4.UI.action.IAction;
import task4.exception.CustomScanner;

public class AddRequest extends CreateManager implements IAction {
  private CustomScanner customScanner;

  public AddRequest(CustomScanner customScanner) {
    this.customScanner = customScanner;
  }

  @Override
  public void execute() {
    System.out.println("Enter the name of the book");
    String request;
      request = customScanner.getString();
      manager.addRequest(request);
      manager.updateRequestCsv();
      System.out.println("Request has been added");
  }
}
