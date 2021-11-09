package ch.zhaw.ads;

import org.junit.Test;
import org.junit.Before;
import java.util.*;
import static org.junit.Assert.*;

public class ADS3_4_test {
    List<Competitor> rankGood;
    List<Competitor> rankTest;
    String textGood;
    String textTest;

    public List<Competitor> createList(String rankingText) {
        List<Competitor> competitorList = new LinkedList<>();
        String[] lines = rankingText.split("\n");
        for (String line : lines) {
            String name = line.split(";")[0];
            String time = line.split(";")[1];
            competitorList.add(new Competitor(0, name,  time));
        }
        return competitorList;
    }

    public String createSortedText(List<Competitor> competitorList) {
        StringBuilder sb = new StringBuilder();
        for (Competitor c : competitorList) {
            sb.append(c +"\n");
        }
        return sb.toString();
    }


    @Before
    public void setUp() throws Exception {
        String rangliste =
                "Ancay Tarcis;02:20:02\n"+
                        "Elmer Beat;02:33:53\n"+
                        "Kiptum Daniel;02:11:31\n"+
                        "Kreibuhl Christian;02:21:47\n"+
                        "Kuehni Martin;02:33:36\n" +
                        "Marti Adrian;02:30:09\n"+
                        "Menzi Christoph;02:27:26\n"+
                        "Mueller Stefan;02:31:14\n"+
                        "Oliver Ruben;02:32:12\n"+
                        "Ott Michael;02:33:48\n";
        rankGood = createList(rangliste);
        textGood = createSortedText(rankGood);
        rankTest = createList(rangliste);
        textTest = new RankingListServer().createNameList(rankTest);
    }
    private String clean(String s) {
        return s.trim();
    }

    @Test
    public void testCreateText() {
        String[] good,test;
        good = textGood.split("\n");
        test = textTest.split("\n");
        assertEquals("length",good.length,test.length);
        for (int i = 0; i < good.length;i++) {
            assertEquals("rangliste["+i+"]",clean(good[i]),clean(test[i]));
            }

    }

}
