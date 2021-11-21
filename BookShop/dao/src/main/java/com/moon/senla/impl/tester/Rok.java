package com.moon.senla.impl.tester;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class Rok implements Music {
  private final List<String> list = new ArrayList<>();

  {
    list.add("rok1");
    list.add("rok2");
    list.add("rok3");
  }

  @Override
  public List<String> getMusic() {
    return list;
  }
}
