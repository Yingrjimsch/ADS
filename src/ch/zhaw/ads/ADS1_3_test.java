package ch.zhaw.ads;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;
import java.util.*;


/*
interface Stack {
    public void push (Object x) throws StackOverflowError;
    public Object pop ();
    public boolean isEmpty();
    public Object peek ();
    public void removeAll ();
    public boolean isFull();
}


class ListStack implements Stack{
    private List list = new LinkedList();

    public void push(Object o){
        list.add(0,o);
    }

    public Object pop(){
        if (isEmpty()) return null;
        return list.remove(0);
    }

    public Object peek(){
        return list.get(0);
    }

    public boolean isEmpty(){
        return list.isEmpty();
    }

    public void removeAll(){
        list.clear();
    }

    public boolean isFull(){
        return false;
    }

    public int size() {
        return list.size();
    }
}
*/

public class ADS1_3_test {
    BracketServer bs;

    @Before
    public void setUp() throws Exception {
        bs = new BracketServer();
    }

    private void test(String s, boolean b) {
        assertEquals(s,b,bs.checkBrackets(s));
    }

    @Test
    public void testBracket() {
        test("(",false);
        test(")",false);
        test("()",true);
        test("(()]",false);
        test("((([([])])))",true);
        test("[(])",false);
        test("[(3 +3)* 35 +3]* {3 +2}",true);
        test("[({3 +3)* 35} +3]* {3 +2}",false);
        test("<(<>)>",true);
        test("<(<)>>",false);
        test("/* hallo */",true);
        test("/*/* */",false);
        test("/*",false);
    }
}
