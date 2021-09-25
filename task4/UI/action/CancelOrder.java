package task4.UI.action;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CancelOrder extends CreateManager implements IAction {

  @Override
  public void execute() {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    System.out.println("Enter ID of the order you want to cancel");
    int id = -1;
    try {
      id = Integer.parseInt(reader.readLine());
    } catch (IOException e) {
      e.printStackTrace();
    }
    manager.cancelOrder(id);
    manager.updateOrderCsv();
    System.out.println("The order has been canceled");
  }
}
