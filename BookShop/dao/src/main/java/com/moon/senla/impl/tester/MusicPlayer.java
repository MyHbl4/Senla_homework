package com.moon.senla.impl.tester;

import java.util.Random;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MusicPlayer {
  @Value("${musicPlayer.name}")
  private String name;
  @Value("${musicPlayer.volume}")
  private int volume;

  public String getName() {
    return name;
  }

  public int getVolume() {
    return volume;
  }

  private Pop pop;
  private Rok rok;
  private Rap rap;

  public MusicPlayer(Pop pop, Rok rok, Rap rap) {
    this.pop = pop;
    this.rok = rok;
    this.rap = rap;
  }

  public void play(Kind kind) {
    Random r = new Random();
    int random = r.nextInt(3);

    if (kind == Kind.POP) {
      System.out.println(pop.getMusic().get(random));
    } else if (kind == Kind.ROK) {
      System.out.println(rok.getMusic().get(random));
    } else {
      System.out.println(rap.getMusic().get(random));
    }
  }
}
