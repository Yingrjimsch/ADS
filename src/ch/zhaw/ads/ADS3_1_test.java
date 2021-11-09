package ch.zhaw.ads;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

public class ADS3_1_test {
    Competitor c1,c2,c3,c4;

    @Before
    public void setUp() throws Exception {
        c1 = new Competitor(0,"Mueller Stefan","02:31:14");
        c2 = new Competitor(0,"Marti Adrian","02:30:09");
        c3 = new Competitor(0,"Kiptum Daniel","02:11:31");
        c4 = new Competitor(0,"Speedy Gonzales","1:11:31");
    }

    @Test
    public void testEquals() {
        assertTrue(c3.getTime() + " == "+c3.getTime(), c3.equals(c3));
        assertTrue(c3.getTime() + " != "+c2.getTime(), !c3.equals(c2));
        assertTrue("equals(null)", !c3.equals(null));
        assertTrue("equals(new Object())", !c3.equals(new Object()));
    }

    @Test
    public void testCompare() {
        assertTrue(c1.getName() +" "+c1.getTime() + " > "+c2.getTime(), c1.compareTo(c2) > 0);
        assertTrue(c2.getName() +" "+c2.getTime() + " < "+c1.getTime(), c2.compareTo(c1) < 0);
        assertTrue(c3.getName() +" "+c3.getTime() + " == "+c3.getTime(), c3.compareTo(c3) == 0);
        assertTrue(c4.getName() +" "+c4.getTime() + " < "+c2.getTime(), c4.compareTo(c2) < 0);
    }
}