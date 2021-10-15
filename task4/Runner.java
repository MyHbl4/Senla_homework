package task4;

import task4.DI.factory.ObjectFactory;
import task4.UI.MenuController;

public class Runner {

  public static void main(String[] args) {
    MenuController menuController = ObjectFactory.getInstance().createObject(MenuController.class);
    menuController.run();
  }
}
