package task4.UI.action;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CompletedOrder extends CreateManager implements IAction {

  @Override
  public void execute() {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    System.out.println("Enter for how many months, display the number of completed orders");
    int months = -1;
    try {
      months = Integer.parseInt(reader.readLine());
    } catch (IOException e) {
      e.printStackTrace();
    }
    manager.getCompletedOrder(months);
  }
}
