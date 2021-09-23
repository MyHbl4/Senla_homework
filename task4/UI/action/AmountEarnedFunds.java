package task4.UI.action;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class AmountEarnedFunds extends CreateManager implements IAction {

  @Override
  public void execute() {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    System.out.println("Enter the number of months to display the income");
    int months = -1;
    try {
      months = Integer.parseInt(reader.readLine());
    } catch (IOException e) {
      e.printStackTrace();
    }
    manager.getAllPriceOfSoldBooks(months);
  }
}
