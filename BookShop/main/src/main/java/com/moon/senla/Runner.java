package com.moon.senla;

import com.moon.senla.factory.ObjectFactory;

public class Runner {

  public static void main(String[] args) {
    MenuController menuController = ObjectFactory.getInstance().createObject(MenuController.class);
    menuController.run();
  }
}
