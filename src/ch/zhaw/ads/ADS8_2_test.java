/**
 * @author K Rege
 * @version 1.00 2018/3/17
 * @version 1.01 2021/8/1
 */

package ch.zhaw.ads;


import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;
import java.util.Collections;
import java.util.List;
import java.util.LinkedList;
import java.io.*;


public class ADS8_2_test {
    LabyrinthServer labyrinthServer;
    Graph<DijkstraNode, Edge> graph;
    String fileToTest = "LabyrinthServer.java";

    @Test
    public void init() throws Exception {
        String labyrinth = "0-6 4-6\n" + "4-6 7-6\n" + "7-6 9-6\n" + "7-6 7-4\n"
                + "7-4 6-4\n" + "7-4 9-4\n" + "9-4 9-1\n" + "7-4 7-1\n" + "7-1 5-1\n"
                + "4-6 4-4\n" + "4-4 4-3\n" + "4-4 1-4\n" + "1-4 1-1\n" + "1-1 3-1\n"
                + "3-1 3-2\n" + "3-1 3-0\n";
        labyrinthServer = new LabyrinthServer();
        graph = labyrinthServer.createGraph(labyrinth);
    }

    private void testEdge(String startName, String destName) {
        DijkstraNode<Edge> node = graph.findNode(startName);
        for (Edge<DijkstraNode> edge : node.getEdges()) {
            if (edge.getDest().getName().equals(destName)) {
                return;
            }
        }
        fail(startName + " not connected to " + destName);
    }

    @Test
    public void testCreateGraph() throws Exception {
        init();
        testEdge("0-6", "4-6");
        testEdge("4-6", "0-6");
        testEdge("1-4", "1-1");
        testEdge("3-1", "3-0");
        testEdge("3-0", "3-1");
    }

}