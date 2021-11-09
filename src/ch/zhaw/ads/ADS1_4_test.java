package ch.zhaw.ads;


import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;


/**
 * @(#)XmlTest.java
 *
 *
 * @author
 * @version 1.00 2017/8/30
 */

/*
interface Stack {
    void push(Object x) throws StackOverflowError;
    Object pop();
    boolean isEmpty();
    Object peek();
    void removeAll();
    boolean isFull();
}


class ListStack implements Stack {
    private final List list = new LinkedList();

    public void push(Object o) {
        list.add(0, o);
    }

    public Object pop() {
        if (isEmpty()) {
            return null;
        }
        return list.remove(0);
    }

    public Object peek() {
        return list.get(0);
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public void removeAll() {
        list.clear();
    }

    public boolean isFull() {
        return false;
    }

    public int size() {
        return list.size();
    }
}
*/


public class ADS1_4_test {
    WellformedXmlServer xml;

    @Before
    public void setUp() throws Exception {
        xml = new WellformedXmlServer();
    }

    private void test(String s, boolean b) {
        boolean ok = xml.checkWellformed(s);
        assertEquals(s, ok, b);
    }

    @Test
    public void testXmlAttributes() {
        test("<a href=\"sugus\"></a>", true);
    }

    @Test
    public void testXml() {
        test("<a></a>", true);
        test("<a>", false);
        test("</a>", false);
        test("<a/>", true);
        test("<a><b></b></a>", true);
        test("<a><b></a></b>", false);
    }
}
