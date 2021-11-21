package com.moon.senla.impl.tester;

import java.util.Random;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Play {
  public static void main(String[] args) {
    //
    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);

//    Music music = context.getBean("rok", Music.class);
//
//    MusicPlayer mp = new MusicPlayer(music);
//
//    mp.play();
//    Music music2 = context.getBean("rap", Music.class);
//
//    MusicPlayer mp2 = new MusicPlayer(music2);
//
//    mp2.play();
    Random random = new Random();
    int r = random.nextInt(3);
    Kind kind=null;
    if (r==0){
      kind = Kind.POP;
    }else if (r==1){
      kind = Kind.ROK;
    }else{
      kind = Kind.RAP;
    }
    MusicPlayer mp = context.getBean("musicPlayer", MusicPlayer.class);
    mp.play(kind);
    System.out.println(mp.getName());
    System.out.println(mp.getVolume());

    Pop pop = context.getBean("pop", Pop.class);
    Pop pop2 = context.getBean("pop", Pop.class);
    System.out.println((pop.getMusic()==pop2.getMusic()));

//    Computer cp = context.getBean("computer", Computer.class);
//    System.out.println(cp.toString());
    context.close();
  }
}
