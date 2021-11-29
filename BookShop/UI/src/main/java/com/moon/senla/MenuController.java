package com.moon.senla;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MenuController {

  private final Builder builder;
  private final Navigator navigator;

  @Autowired
  public MenuController(Builder builder, Navigator navigator) {
    this.builder = builder;
    builder.buildMenu();
    this.navigator = navigator;
  }

  public void run() {
    navigator.helloShop();
    navigator.setCurrentMenu(builder.getRootMenu());
    navigator.printMenu();
    navigator.navigate();
  }
}
