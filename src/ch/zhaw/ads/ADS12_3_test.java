package ch.zhaw.ads;

import org.junit.Test;
import static org.junit.Assert.*;

public class ADS12_3_test {
    ParallelQuickerSortServer sortServer = new ParallelQuickerSortServer();
    final int DATAELEMS = 10000;

    String fileToTest = "QuickerSortServer.java";

    @Test
    public void testRandomData() throws Exception {
        sortServer.dataElems = DATAELEMS;
        sortServer.insertion_threshold = 50;
        int[] data = sortServer.randomData();
        Thread rootThread = new ParallelQuickerSortServer(data, 0, data.length - 1);
        rootThread.start();
        try {
            rootThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int i = 0; i< sortServer.dataElems-1; i++) {
            assertTrue("Sorted",data[i] <= data[i+1]);
        }
    }

    @Test
    public void testAscendingData() throws Exception {
        sortServer.dataElems = DATAELEMS;
        sortServer.insertion_threshold = 50;
        int[] data = sortServer.ascendingData();
        Thread rootThread = new ParallelQuickerSortServer(data, 0, data.length - 1);
        rootThread.start();
        try {
            rootThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int i = 0; i< sortServer.dataElems-1; i++) {
            assertTrue("Sorted",data[i] <= data[i+1]);
        }
    }

    @Test
    public void testDescendingData() throws Exception {
        sortServer.dataElems = DATAELEMS;
        sortServer.insertion_threshold = 50;
        int[] data = sortServer.descendingData();
        Thread rootThread = new ParallelQuickerSortServer(data, 0, data.length - 1);
        rootThread.start();
        try {
            rootThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int i = 0; i< sortServer.dataElems-1; i++) {
            assertTrue("Sorted",data[i] <= data[i+1]);
        }
    }

    @Test
    public void testCompleteData() throws Exception {
        boolean found;
        sortServer.dataElems = DATAELEMS;
        sortServer.insertion_threshold = 50;
        int[] data = sortServer.ascendingData();
        int[] original = new int[data.length];
        System.arraycopy(data, 0, original, 0, data.length);
        Thread rootThread = new ParallelQuickerSortServer(data, 0, data.length - 1);
        rootThread.start();
        try {
            rootThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int i = 0; i <= sortServer.dataElems-1; i++) {
            found = false;
            for (int j = 0; j <= sortServer.dataElems - 1; j++) {
                if (data[i] == original[j]) {
                    found = true;
                    break;
                }
            }
            assertTrue("Data not found after sort", found);
            if (!found) break;
        }
    }

}