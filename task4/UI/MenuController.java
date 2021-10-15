package task4.UI;

import java.util.Scanner;
import task4.DI.factory.ObjectFactory;
import task4.UI.action.BookStoreManager;
import task4.UI.action.Manager;

public class MenuController {

  private static MenuController instance;
  private Builder builder;
  private Navigator navigator;
  private Manager manager = ObjectFactory.getInstance().createObject(Manager.class);

  public MenuController() {
    builder = Builder.getInstance(manager);
    builder.buildMenu();
    navigator = Navigator.getInstance(manager);
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
