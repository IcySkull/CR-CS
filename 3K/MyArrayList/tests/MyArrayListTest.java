import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertThat;

import static org.junit.Assert.*;

public class MyArrayListTest {
    static final int limit = MyArrayList.THRESHOLD;

    @Test
    public void isEmpty() {
        MyArrayList<String> a = new MyArrayList<>();
        assertTrue(a.isEmpty());
        a.add("asd");
        assertFalse(a.isEmpty());
        a.clear();
        assertTrue(a.isEmpty());
    }

    @Test
    public void size() {
    }

    @Test
    public void addCheckBounds() {
        MyArrayList<String> a = new MyArrayList<>();
        for(int i = 1; i <= 24; i++) {
            fillToNextBound(a);
            assertArrayEquals(getArrayToBound(i), a.ary);
        }
    }

    @Test
    public void addItemIndex() {
        MyArrayList<String> a = new MyArrayList<>();
        a.add("1");
        a.add("2");
        a.add("3");
        a.add("4");
        a.add("5");
        a.add("6");
        assertEquals(6, a.size());
        String[] exp = new String[limit*2];
        exp[0] = "1";
        exp[1] = "2";
        exp[2] = "3";
        exp[3] = "4";
        exp[4] = "5";
        exp[5] = "6";
        assertArrayEquals(a.ary, exp);
    }

    @Test
    public void testAdd() {
        ArrayList<String> a = new ArrayList<>();
        int elm = 3000000;
        for (int i = 0; i < elm; i++) {
            a.add(String.valueOf(i));
        }
        assertEquals(elm, a.size());
        for (int i = elm-1; i >= 0; i--) {
            a.remove(i);
        }
        String[] exp = new String[0];
        assertArrayEquals(exp, a.toArray());
    }

    @Test
    public void remove() {
    }

    @Test
    public void get() {
    }

    @Test
    public void clear() {
    }

    @Test
    public void iterator() {
    }

    /**
     * fill the bound with Strings
     * @param list
     * @param bound the bound wanted to fill
     */
    private void fillBound(MyArrayList<String> list, int bound) {
        if (bound < 1) {
            throw new IllegalStateException();
        }
        int thrs = limit*((int)(Math.pow(2, bound)));
        for (int i = bound == 1 ? 0 : limit*((int)(Math.pow(2, bound-1))); i < thrs; i++) {
            list.add("asd", i);
        }
    }

    private void fillToNextBound(MyArrayList list) {
        int upper = list.ary.length - list.size();
        for (int i = 0; i < upper; i++) {
            list.add("asd");
        }
    }

    private String[] getArrayToBound(int bound) {
        String[] ary = new String[limit*((int)Math.pow(2, bound))];
        int upper = limit*((int)Math.pow(2, bound-1));
        for (int i = 0; i < upper; i++) {
            ary[i] = "asd";
        }
        return ary;
    }
}