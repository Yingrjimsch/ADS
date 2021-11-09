package ch.zhaw.ads;

import java.lang.reflect.Array;
import java.util.*;

public class RouteServer implements CommandExecutor {
    /*
    build the graph given a text file with the topology
    */
    public Graph<DijkstraNode, Edge> createGraph(String topo) throws Exception {
      Graph<DijkstraNode, Edge> graph = new AdjListGraph<>(DijkstraNode.class, Edge.class);
      Arrays.stream(topo.split("\n")).forEach(edge -> {
        String[] e = edge.split(" ");
        String from = e[0];
        String to = e[1];
        String weight = e[2];
        try {
          graph.addEdge(from, to, Double.parseDouble(weight));
          graph.addEdge(to, from, Double.parseDouble(weight));
        } catch(Throwable t) {
          System.out.println("Not part of the exercise");
        }
      });
      return graph;
    }


    /*
    apply the dijkstra algorithm
    */
    public void dijkstraRoute(Graph<DijkstraNode, Edge> graph, String from, String to) {
      Queue<DijkstraNode> redNodes = new PriorityQueue<>();

      DijkstraNode start = graph.findNode(from);
      DijkstraNode goal = graph.findNode(to);

      redNodes.add(start);
      while (!redNodes.isEmpty()) {
        DijkstraNode<Edge> curr = redNodes.remove();
        if (curr == goal) return;
        curr.setMark(true);
        for (Edge<DijkstraNode> edge : curr.getEdges()) {
          DijkstraNode neighbour = edge.getDest();
          if (neighbour.getDist() == 0 && neighbour != start) {
            neighbour.setDist(Double.MAX_VALUE);
          }
          if (!neighbour.getMark()) {
            double dist = curr.getDist() + edge.getWeight();
            if (dist < neighbour.getDist()) {
              neighbour.setDist(dist);
              neighbour.setPrev(curr);
              redNodes.remove(neighbour);
              redNodes.add(neighbour);
            }
          }
        }
      }
    }

    /*
    find the route in the graph after applied dijkstra
    the route should be returned with the start town first
    */
    public List<DijkstraNode<Edge>> getRoute(Graph<DijkstraNode, Edge> graph, String to) {
        List<DijkstraNode<Edge>> route = new LinkedList<>();
        DijkstraNode<Edge> town = graph.findNode(to);
        do {
            route.add(0,town);
            town = town.getPrev();
        } while (town != null);
        return route;
    }

    public String execute(String topo) throws Exception {
        Graph<DijkstraNode, Edge> graph = createGraph(topo);
        dijkstraRoute(graph, "Winterthur", "Lugano");
        List<DijkstraNode<Edge>> route = getRoute(graph, "Lugano");
        // generate result string
        StringBuffer buf = new StringBuffer();
        for (DijkstraNode<Edge> rt : route) buf.append(rt+"\n");
        return buf.toString();
    }

    public static void main(String[] args)throws Exception {
        String swiss =
                "Winterthur Zürich 25\n"+
                        "Zürich Bern 126\n"+
                        "Zürich Genf 277\n"+
                        "Zürich Luzern 54\n"+
                        "Zürich Chur 121\n"+
                        "Zürich Berikon 16\n"+
                        "Bern Genf 155\n"+
                        "Genf Lugano 363\n"+
                        "Lugano Luzern 206\n"+
                        "Lugano Chur 152\n"+
                        "Chur Luzern 146\n"+
                        "Luzern Bern 97\n"+
                        "Bern Berikon 102\n"+
                        "Luzern Berikon 41\n";
        RouteServer server = new RouteServer();
        System.out.println(server.execute(swiss));
    }
}
