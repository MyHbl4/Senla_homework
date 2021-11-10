package com.moon.senla;

import java.util.Scanner;

public class CustomScanner {
  private static final Scanner sc = new Scanner(System.in);

  public CustomScanner() {}

  public int getInt() {
    Integer integer;
    do {
      while (!sc.hasNextInt()) {
        System.out.println("Enter the correct value");
        sc.next();
      }
      integer = sc.nextInt();
      if (integer <= 0) {
        System.out.println("Enter the correct value");
      }
    } while (integer <= 0);
    return integer;
  }

  public String getString() {
    String str;
    do {
      while (!sc.hasNext()) {
        sc.nextLine();
      }
      str = sc.nextLine();
    } while (str.trim().isEmpty());
    return str;
  }
}
