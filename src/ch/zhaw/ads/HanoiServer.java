package ch.zhaw.ads;

import com.sun.xml.internal.ws.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;

public class HanoiServer implements CommandExecutor {

  List<String> movements = new ArrayList<>();

  @Override
  public String execute(String command) throws Exception {
    int towerHeight = Integer.parseInt(command);
    hanoi(towerHeight, 'A', 'B', 'C');
    return String.join("\n", movements);
  }

  void hanoi(int towerHeight, char from, char to, char help) {
    if (towerHeight > 0) {
      hanoi(towerHeight - 1, from, help, to);
      movements.add(getMovement(from, to));
      hanoi(towerHeight - 1, help, to, from);
    }
  }

  private String getMovement(char from, char to) {
    return String.format("Lege die oberste Scheibe von Turm %s auf %s", from, to);
  }
}
