package ch.zhaw.ads;

import org.junit.Test;
import static org.junit.Assert.*;

public class ADS12_1_test {
    SortServer sortServer = new SortServer();
    final int DATAELEMS = 10000;

    String fileToTest = "QuickerSortServer.java";

    @Test
    public void testRandomData() throws Exception {
        sortServer.dataElems = DATAELEMS;
        sortServer.insertion_threshold = 50;
        int[] data = sortServer.randomData();
        sortServer.quickerSort(data);
        for (int i = 0; i< sortServer.dataElems-1; i++) {
            assertTrue("Random Data",data[i] <= data[i+1]);
        }
    }

    @Test
    public void testAscendingData() throws Exception {
        sortServer.dataElems = DATAELEMS;
        sortServer.insertion_threshold = 50;
        int[] data = sortServer.ascendingData();
        sortServer.quickerSort(data);
        for (int i = 0; i< sortServer.dataElems-1; i++) {
            assertTrue("ASC Data", data[i] <= data[i + 1]);
        }
    }

    @Test
    public void testDecendingData() throws Exception {
        sortServer.dataElems = DATAELEMS;
        sortServer.insertion_threshold = 50;
        int[] data = sortServer.descendingData();
        sortServer.quickerSort(data);
        for (int i = 0; i< sortServer.dataElems-1; i++) {
            assertTrue("DESC Data", data[i] <= data[i + 1]);
        }
    }

    @Test
    public void testCompleteData() throws Exception {
        boolean found;
        sortServer.dataElems = DATAELEMS;
        sortServer.insertion_threshold = 50;
        int[] data = sortServer.randomData();
        int[] original = new int[data.length];
        System.arraycopy(data, 0, original, 0, data.length);
        sortServer.quickerSort(data);
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