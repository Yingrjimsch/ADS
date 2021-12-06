/**
 * @author K Rege
 * @version 1.00 2018/3/17
 * @version 1.01 2021/8/1
 */

package ch.zhaw.ads;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;
import java.util.*;
import java.io.*;
import java.util.function.*;

public class ADS11_3_test {
    final int DATAELEMS = 10000;
    SortServer sortServer = new SortServer();

    String fileToTest = "SortServer.java";

    @Test
    public void testRandomData() throws Exception {
        sortServer.dataElems = DATAELEMS;
        int[] data = sortServer.randomData();
        assertEquals("Anzahl Daten",sortServer.dataElems,data.length);
        assertTrue("Distributen",data[0] != data[1]);
    }

    @Test
    public void testAscendingData() throws Exception {
        sortServer.dataElems = DATAELEMS;
        int[] data = sortServer.ascendingData();
        assertEquals("Anzahl Daten",sortServer.dataElems,data.length);
        for (int i = 0; i< sortServer.dataElems-1; i++) {
            assertTrue("Distributen",data[i] <= data[i+1]);
        }
    }

    @Test
    public void testDescendingData() throws Exception {
        sortServer.dataElems = DATAELEMS;
        int[] data = sortServer.descendingData();
        assertEquals("Anzahl Daten",sortServer.dataElems,data.length);
        for (int i = 0; i< sortServer.dataElems-1; i++) {
            assertTrue("Distributen",data[i] >= data[i+1]);
        }
    }

    @Test
    public void testBubbleSort() throws Exception {
        sortServer.dataElems = DATAELEMS;
        int[] data = sortServer.randomData();
        sortServer.bubbleSort(data);
        for (int i = 0; i< sortServer.dataElems-1; i++) {
            assertTrue("Sorted",data[i] <= data[i+1]);
        }
    }

    @Test
    public void testInsertionSort() throws Exception {
        sortServer.dataElems = DATAELEMS;
        int[] data = sortServer.randomData();
        sortServer.insertionSort(data);
        for (int i = 0; i< sortServer.dataElems-1; i++) {
            assertTrue("Sorted",data[i] <= data[i+1]);
        }
    }

    @Test
    public void testSelectionSort() throws Exception {
        sortServer.dataElems = DATAELEMS;
        int[] data = sortServer.randomData();
        sortServer.selectionSort(data);
        for (int i = 0; i< sortServer.dataElems-1; i++) {
            assertTrue("Sorted",data[i] <= data[i+1]);
        }
    }

    private double testQuadratic(String msg, Consumer<int[]> sorter) throws Exception {
        sortServer.dataElems = DATAELEMS;
        double time1 = sortServer.measureTime(sortServer::randomData,sorter);

        sortServer.dataElems = DATAELEMS*2;
        double time2 = sortServer.measureTime(sortServer::randomData,sorter);

        assertTrue(msg+" Time O(n^2)",time1 > time2/6 && time1 < time2/2);
        return time1;
    }

    @Test
    public void testMeasureTime() throws Exception {
        double time1 = testQuadratic("BUBBLESORT",sortServer::bubbleSort);
        double time2 = testQuadratic("SELECTIONSORT",sortServer::selectionSort);
        double time3 = testQuadratic("INSERTIONSORT",sortServer::insertionSort);
        // System.out.println(""+time1+" "+time2+" "+time3);
        assertTrue("BubbleTime > 1.5 * SelectionTime",time1 > 1.5* time2);
        assertTrue("SelectionTime > 1.5 * InsertionTime",time2 > 1.5* time3);
    }


}