package ch.zhaw.ads;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

public class ADS1_1_test {
    KgvServer server;

    @Before
    public void setUp() throws Exception {
        server = new KgvServer();
    }

    @Test
    public void testKgv() {
        assertEquals("kgv von 3 4", 12,server.kgv(3,4));
        assertEquals("kgv von 2 4", 4,server.kgv(2,4));
        assertEquals("kgv von 5 7", 35,server.kgv(5,7));
        assertEquals("kgv von 4 6", 12,server.kgv(4,6));
    }
}