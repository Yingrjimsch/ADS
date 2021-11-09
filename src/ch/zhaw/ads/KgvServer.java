package ch.zhaw.ads;

import java.io.ByteArrayInputStream;
import java.util.Scanner;

public class KgvServer implements CommandExecutor {

  private static final String NEW_LINE = "\n";
  @Override
  public String execute(String s) {
    String[] numbers = s.split("[ ,]+");
    int a = Integer.parseInt(numbers[0]);
    int b = Integer.parseInt(numbers[1]);
    return kgv(a, b) + NEW_LINE;
  }

  /*
    ggT(a,b) * kgV(a,b) = |a*b|
    kgV(a,b) = |a*b| / ggT(a,b)
   */
  public int kgv(int a, int b) {
    return Math.abs(a * b) / ggT(a, b);
  }

  private int ggT(int a, int b) {
    while (a != b) {
      if (a > b) a = a - b;
      else b = b - a;
    }
    return a;
  }
}
