package ch.zhaw.ads;

import java.util.*;
import java.awt.*;
import java.util.List;
import java.util.stream.Collectors;


public class LabyrinthServer implements CommandExecutor {
  private final String LINE_BREAK = "\n";
  private final String SPACE = " ";
  private final String MINUS = "-";
	ServerGraphics serverGraphics = new ServerGraphics();

	public Graph<DijkstraNode, Edge> createGraph(String s) {
    Graph<DijkstraNode, Edge> graph = new AdjListGraph<>(DijkstraNode.class, Edge.class);
    Arrays.stream(s.split(LINE_BREAK)).forEach(edge -> {
      String[] e = edge.split(SPACE);
      Point from = new Point(Integer.parseInt(e[0].split(MINUS)[0]),Integer.parseInt(e[0].split(MINUS)[1]));
      Point to = new Point(Integer.parseInt(e[1].split(MINUS)[0]),Integer.parseInt(e[0].split(MINUS)[1]));
      double weight = Math.sqrt(Math.pow(to.x - from.x, 2) + Math.pow(to.y - from.y, 2));
      try {
        graph.addEdge(e[0], e[1], weight);
        graph.addEdge(e[1], e[0], weight);
      } catch(Throwable t) {
        System.out.println("Not part of the exercise");
      }
    });
    return graph;
	}

	public void drawLabyrinth(Graph<DijkstraNode, Edge> graph) {
    serverGraphics.setColor(Color.DARK_GRAY);
    //Dimension screenDimension = Toolkit.getDefaultToolkit().getScreenSize();
    //serverGraphics.fillRect(0, 0, screenDimension.width, screenDimension.height);
    serverGraphics.fillRect(0, 0, 2400, 1800);
    serverGraphics.setColor(Color.WHITE);
    graph.getNodes().forEach((n ->
      n.getEdges().forEach(e ->
        serverGraphics.drawPath(n.getName(), ((Edge<DijkstraNode>)e).getDest().getName(), false))));
	}

	private boolean search(DijkstraNode<Edge> current, DijkstraNode<Edge> end) {
    current.setMark(true);
    if (current == end) return true;
    for (Edge<DijkstraNode> edge : current.getEdges()) {
      DijkstraNode node = edge.getDest();
      if (!node.getMark()) {
        if (search(node, end)) {
          node.setPrev(current);
          return true;
        }
      }
    }
    current.setMark(false);
    return false;
	}

	public void drawRoute(Graph<DijkstraNode, Edge> graph, String startNode, String endNode) {
    serverGraphics.setColor(Color.RED);
    search(graph.findNode(startNode), graph.findNode(endNode));
    DijkstraNode<Edge> current = graph.findNode(endNode);
    while (current.getPrev() != null) {
      serverGraphics.drawPath(current.getName(), current.getPrev().getName(), true);
      current = current.getPrev();
    }
	}

	public String execute(String s) throws Exception {
		Graph<DijkstraNode, Edge> graph;
		graph = createGraph(s);
		drawLabyrinth(graph);
		drawRoute(graph, "0-6", "3-0");
		return serverGraphics.getTrace();
	}

}
