package com.moon.senla;

import com.moon.senla.action.Manager;
import com.moon.senla.factory.ObjectFactory;

public class MenuController {

  private static MenuController instance;
  private final Builder builder;
  private final Navigator navigator;
  private final Manager manager = ObjectFactory.getInstance().createObject(Manager.class);

  public MenuController() {
    builder = Builder.getInstance(manager);
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
    navigator.helloShop();
    navigator.setCurrentMenu(builder.getRootMenu());
    navigator.printMenu();
    navigator.navigate();
  }
}
