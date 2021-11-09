package ch.zhaw.ads;

import org.junit.Test;
import org.junit.Before;
import java.util.*;
import static org.junit.Assert.*;
import java.util.concurrent.atomic.*;

public class ADS5_3_test {
    Tree<Competitor> rankGood;
    Tree<Competitor> rankTest;
    String textGood;
    String textTest;

    public Tree<Competitor> createTree(String rankingText) {
        Tree<Competitor> competitorTree = new SortedBinaryTree<>();
        String[] lines = rankingText.split("\n");
        for (String line : lines) {
            String name = line.split(";")[0];
            String time = line.split(";")[1];
            competitorTree.add(new Competitor(0, name,  time));
        }
        return competitorTree;
    }

    public String createSortedText(Tree<Competitor> competitorTree) {
        AtomicInteger rank = new AtomicInteger();
        rank.set(1);
        StringBuilder sb = new StringBuilder();
        competitorTree.traversal().inorder(c -> {c.setRank(rank.getAndIncrement()); sb.append(c +"\n");});
        return sb.toString();
    }


    @Before
    public void setUp() throws Exception {
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
        rankGood = createTree(rangliste);
        textGood = createSortedText(rankGood);
        rankTest = new RankingTreeServer().createTree(rangliste);
        textTest = new RankingTreeServer().createSortedText(rankTest);
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