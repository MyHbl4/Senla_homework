package task4;

import task4.DI.factory.ObjectFactory;
import task4.UI.MenuController;
import task4.jdbc.CreateDB;

public class Runner {

  public static void main(String[] args) {
    new CreateDB().create();
    MenuController menuController = ObjectFactory.getInstance().createObject(MenuController.class);
    menuController.run();
  }
}
