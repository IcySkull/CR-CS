import static org.junit.Assert.assertEquals;

import java.util.Iterator;
import java.util.List;

import org.junit.Test;

public class StackTestTest {

    @Test
    public void testSetStackABC() {
        String line = "a b c d e f g h i";
        StackTest stest = new StackTest(line);
        Iterator<String> itr = stest.popEmAll();
        assertEquals(List.of("i", "h", "g", "f", "e", "d", " c", "b", "a"), itr);
        assertEquals("i h g f e d c b a", StackTest.collectItr(itr));
    }
}
