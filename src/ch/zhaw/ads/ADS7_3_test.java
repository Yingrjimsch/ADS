/**
 * @(#)TreeTest.java
 *
 *
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

public class ADS7_3_test {
    RouteServer routeServer;
    Graph<DijkstraNode, Edge> graph;
    String fileToTest = "RouteServer.java";

    private void init() throws Exception {
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
        routeServer = new RouteServer();
        graph =  routeServer.createGraph(swiss);
    }

    private void testDest(DijkstraNode<Edge> start, String destName, double dist) {
        for (Edge road: start.getEdges()) {
            DijkstraNode destTown = (DijkstraNode) road.getDest();
            if (destName.equals(destTown.getName())) {
                assertEquals(start.getName() + " to "+ destName,dist,road.getWeight(),1E-10);
                return;
            }
        }
        fail(destName + " not connected to " + start.getName());
    }


    @Test
    public void testCreateGrasphEdges() throws Exception {
        init();
        DijkstraNode<Edge> town = graph.findNode("Luzern");
        assertNotNull("Luzern",town);
        testDest(town,"Bern",97);
        testDest(town,"Berikon",41);
        testDest(town,"Chur",146);
        testDest(town,"Lugano",206);
        testDest(town,"Zürich",54);
        testDest(town,"Berikon",41);
    }

    @Test
    public void testCreateGrasphNodes() throws Exception {
        init();
        DijkstraNode<Edge> town = graph.findNode("Luzern");
        assertNotNull("Luzern",town);
        town = graph.findNode("Winterthur");
        assertNotNull("Winterthur",town);
        town = graph.findNode("Paris");
        assertNull("Paris",town);
    }

}