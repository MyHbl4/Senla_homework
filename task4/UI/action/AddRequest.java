package task4.UI.action;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class AddRequest extends CreateManager implements IAction {

  @Override
  public void execute() {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    System.out.println("Enter the name of the book");
    String request = null;
    try {
      request = reader.readLine();
    } catch (IOException e) {
      e.printStackTrace();
    }
    manager.addRequest(request);
    System.out.println("Request has been added");
  }
}
