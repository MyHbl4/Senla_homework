package task4.UI;

import java.util.Scanner;
import task4.DI.injector.BeanFactory;
import task4.UI.action.Manager;

public class MenuController {

  private static MenuController instance;
  Scanner scanner = new Scanner(System.in);
  private Builder builder;
  private Navigator navigator;

  public MenuController(Manager manager) {
    builder = Builder.getInstance(manager);
    builder.buildMenu();
    navigator = Navigator.getInstance(manager);
  }

  public static MenuController getInstance(Manager manager) {
    if (instance == null) {
      instance = new MenuController(manager);
    }
    return instance;
  }

  public void run() {
    navigator.loadCsv();
    navigator.helloShop();
    navigator.setCurrentMenu(builder.getRootMenu());
    navigator.printMenu();
    navigator.navigate();
  }
}
