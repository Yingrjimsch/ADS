/**
 * @author K Rege
 * @version 1.00 2018/3/17
 * @version 1.01 2021/8/1
 */

package ch.zhaw.ads;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;


public class ADS10_3_test {
    FuzzySearchServer fs;

    String fileToTest = "FuzzySearchServer.java";

    private void init() throws Exception {
        fs.names.clear();
        fs.trigrams.clear();
        String rangliste =
                "Mueller Stefan;02:31:14\n"+
                        "Marti Adrian;02:30:09\n"+
                        "Kiptum Daniel;02:11:31\n"+
                        "Ancay Tarcis;02:20:02\n"+
                        "Kreibuhl Christian;02:21:47\n"+
                        "Ott Michael;02:33:48\n"+
                        "Menzi Christoph;02:27:26\n"+
                        "Oliver Ruben;02:32:12\n"+
                        "Elmer Beat;02:33:53\n"+
                        "Kuehni Martin;02:33:36\n";
        fs.loadNames(rangliste);
    }

    @Test
    public void testLoadNames() throws Exception {
        init();
        assertEquals("Länge von 'names' Liste",10,fs.names.size());
        assertEquals("Erster Name","Mueller Stefan",fs.names.get(0));
    }

    @Test
    public void testTrigramForName() throws Exception {
        init();
        List<String> trigList = fs.trigramForName("Heinz");
        assertEquals("Länge von Trigram Liste",5,trigList.size());
        String[] good = {" he","hei","ein","inz","nz "};
        for (int i = 0; i < good.length; i++) {
            assertEquals("trigram ["+i+"]",good[i],trigList.get(i));
        }
    }

    @Test
    public void testAddToTrigrams() throws Exception {
        init();
        fs.addToTrigrams(0,"mue");
        fs.addToTrigrams(0,"uel");
        fs.addToTrigrams(1,"mar");
        assertEquals("Länge von 'trigram'",3,fs.trigrams.size());
        assertEquals("mue",0,(int)fs.trigrams.get("mue").get(0));
        assertEquals("uel",0,(int)fs.trigrams.get("uel").get(0));
        assertEquals("mar",1,(int)fs.trigrams.get("mar").get(0));
    }

    @Test
    public void testFind() throws Exception {
        init();
        fs.constructTrigramIndex(fs.names);
        assertEquals("","Kiptum Daniel",fs.find("Kiptum Daniel",80));
        assertEquals("","Kiptum Daniel",fs.find("Daniel Kiptum",80));
        assertEquals("","Kiptum Daniel",fs.find("Kip Dan",30));
        assertEquals("","Kiptum Daniel",fs.find("Dan Kip",30));
    }

}
