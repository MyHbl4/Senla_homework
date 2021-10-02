package task4.UI;

import java.util.Scanner;

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
    navigator.loadCsv();
    navigator.helloShop();
    navigator.setCurrentMenu(builder.getRootMenu());
    navigator.printMenu();
    navigator.navigate();
  }
}
