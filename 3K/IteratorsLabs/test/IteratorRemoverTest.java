import junit.framework.TestCase;

public class IteratorRemoverTest extends TestCase {

    public void testRemove() {
        IteratorRemover a = new IteratorRemover("a b c a b c a", "a");
        IteratorRemover b = new IteratorRemover("a b c d e f g h i j x x x x", "x");
        IteratorRemover c = new IteratorRemover("1 2 3 4 5 6 a b c a b c", "b");


        assertEquals("[b, c, b, c]", a.toString());
        assertEquals("[a, b, c, d, e, f, g, h, i, j]", b.toString());
        assertEquals("[1, 2, 3, 4, 5, 6, a, c, a, c]", c.toString());
    }
}