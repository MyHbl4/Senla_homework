package task4.UI;

import java.util.Scanner;
import task4.UI.action.Manager;

public class MenuController {

  private static MenuController instance;
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
    new Manager().getBookService().downloadBookCsv();
    new Manager().getOrderService().downloadOrderCsv();
    new Manager().getRequestService().downloadRequestCsv();
    System.out.println("\n::BOOK STORE::\n*******************");
    navigator.setCurrentMenu(builder.getRootMenu());
    navigator.printMenu();
    boolean isValid = true;
    while (isValid) {
      int index = -1;
      index = scanner.nextInt();
      if (index == 0) {
        isValid = false;
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
