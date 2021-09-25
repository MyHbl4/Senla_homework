package task4.UI;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;
import task4.UI.action.Manager;

public class MenuController {

  private static MenuController instance;
  BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
  Scanner scanner = new Scanner(System.in);
  private Builder builder;
  private Navigator navigator;

  public MenuController() {
    builder = Builder.getInstance();
    builder.buildMenu();
    navigator = Navigator.getInstance();
  }

  public static MenuController getInstance() {
    if (instance == null) {
      instance = new MenuController();
    }
    return instance;
  }

  public void run() {
    new Manager().downloadBookCsv();
    new Manager().downloadOrderCsv();
    new Manager().downloadRequestCsv();
    System.out.println("\n::BOOK STORE::\n*******************");
    navigator.setCurrentMenu(builder.getRootMenu());
    navigator.printMenu();
    boolean flag = true;
    while (flag) {
      int index = -1;
      index = scanner.nextInt();
      if (index == 0) {
        flag = false;
        System.out.printf("The program is closed");
      } else if (index > 0 && index <= navigator.getCurrentMenu().getMenuItems().size()) {
        navigator.navigate(index - 1);
        navigator.printMenu();
      } else {
        System.out.println("Enter the correct value");
      }
    }
  }
}
