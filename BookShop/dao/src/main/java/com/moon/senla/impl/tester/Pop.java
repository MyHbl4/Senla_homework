package com.moon.senla.impl.tester;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class Pop implements Music {
  private List<String> list = new ArrayList<>();

  {
    list.add("pop1");
    list.add("pop2");
    list.add("pop3");
  }

  @Override
  public List<String> getMusic() {
    return list;
  }
}
