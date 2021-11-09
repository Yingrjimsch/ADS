package ch.zhaw.ads;

import java.util.concurrent.ExecutorService;

public class SnowflakeServer implements CommandExecutor {

  private Turtle turtle;

  @Override
  public String execute(String command) {
    turtle = new Turtle(0.2,0.75);
    int depth = Integer.parseInt(command);
    snowFlake(depth, 0.7);
    turtle.turn(-120);
    snowFlake(depth, 0.7);
    turtle.turn(-120);
    snowFlake(depth, 0.7);
    return turtle.getTrace();
  }

  void snowFlake(int depth, double distance) {
    if (depth == 0) {
      turtle.move(distance);
    } else {
      depth--;
      distance = distance/3;
      snowFlake(depth, distance);
      turtle.turn(60);
      snowFlake(depth, distance);
      turtle.turn(-120);
      snowFlake(depth, distance);
      turtle.turn(60);
      snowFlake(depth, distance);
    }
  }
}
