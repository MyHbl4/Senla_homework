package com.moon.senla;

import com.moon.senla.factory.ObjectFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Runner {
  private static final Logger logger = LoggerFactory.getLogger(Runner.class);

  public static void main(String[] args) {
    new LoggerProperty();
    MenuController menuController = ObjectFactory.getInstance().createObject(MenuController.class);
    menuController.run();
    logger.info("The program is closed");
  }
}
