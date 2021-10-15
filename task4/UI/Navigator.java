package task4.UI;

import java.util.Scanner;
import task4.DI.factory.ObjectFactory;
import task4.UI.action.Manager;

public class Navigator {

  private static Navigator instance;
  private Menu currentMenu;
  private Manager manager;

  private Navigator(Manager manager) {
    this.manager = manager;
  }

  public static Navigator getInstance(Manager manager) {
    if (instance == null) {
      instance = new Navigator(manager);
    }
    return instance;
  }

  public void printMenu() {
    System.out.println(currentMenu);
  }

  public void helloShop() {
    System.out.println("\n::BOOK STORE::\n*******************");
  }

  public void loadCsv() {
    manager.getBookService().readBookBd();
    manager.getOrderService().readOrderBd();
    manager.getRequestService().readRequestBd();
  }

  public void navigate() {
    Scanner scanner = new Scanner(System.in);
    boolean isValid = true;
    while (isValid) {
      int index;
      index = scanner.nextInt();
      if (index == 0) {
        isValid = false;
        System.out.println("The program is closed");
      } else if (index > 0 && index <= getCurrentMenu().getMenuItems().size()) {
        if (currentMenu != null) {
          MenuItem menuItem = currentMenu.getMenuItems().get(index - 1);
          menuItem.doAction();
          currentMenu = menuItem.getNextMenu();
        }
        printMenu();
      } else {
        System.out.println("Enter the correct value");
      }
    }
  }

  public Menu getCurrentMenu() {
    return currentMenu;
  }

  public void setCurrentMenu(Menu currentMenu) {
    this.currentMenu = currentMenu;
  }
}
