package com.moon.senla;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Runner {
  private static final Logger logger = LoggerFactory.getLogger(Runner.class);

  public static void main(String[] args) {
    new LoggerProperty();
    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
    context.getBean(MenuController.class).run();

    logger.info("The program is closed");
  }
}
