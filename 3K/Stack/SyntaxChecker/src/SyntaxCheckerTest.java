import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class SyntaxCheckerTest {
    SyntaxChecker sc;

    @Before
    public void start() {
        sc = new SyntaxChecker();
    }

    @Test
    public void testCheckExpression() {
        sc.setExpression("(abc(*def)");
        assertFalse(sc.checkExpression());

        sc.setExpression("[{}]");
        assertTrue(sc.checkExpression());

        sc.setExpression("[");
        assertFalse(sc.checkExpression());

        sc.setExpression("[{<()>}]");
        assertTrue(sc.checkExpression());

        sc.setExpression("{<html[value=4]*(12)>{$x}}");
        assertTrue(sc.checkExpression());

        sc.setExpression("[one]<two>{three}(four)");
        assertTrue(sc.checkExpression());

        sc.setExpression("car(cdr(a)(b)))");
        assertFalse(sc.checkExpression());

        sc.setExpression("car(cdr(a)(b))");
        assertTrue(sc.checkExpression());

        sc.setExpression("<<<animal(dog(breed=Siberian husky)(color=BrownBlackWhite))>>");
        assertFalse(sc.checkExpression());

        sc.setExpression("<?xml version=\"1.0\" encoding=\"UTF-8\"?><CATALOG><PLANT><COMMON>Marsh Marigold</COMMON><BOTANICAL>{Caltha palustris)</BOTANICAL><PLANT>");
        assertFalse(sc.checkExpression());

        sc.setExpression("<html dir=\"ltr\" lang=\"en\"><head><meta charset=\"utf-8\"><title>Syntax Checker</title><style>body {background: #FFFFFF;margin: 0;}</style></head><body></body></html>");
        assertTrue(sc.checkExpression());

        sc.setExpression("<html dir=\"ltr\" lang=\"en\"><head><meta charset=\"utf-8\"><title>Syntax Checker Fail<style>>body {background: #FFFFFF;margin: 0;}</style></head><body></body></html>");
        assertFalse(sc.checkExpression());

        sc.setExpression("]{Type[String]}");
        assertFalse(sc.checkExpression());

        sc.setExpression("{(Type)[String])}");
        assertFalse(sc.checkExpression());

        sc.setExpression("<script type=\"module\" src=\"new_tab_page.js\"></script>");
        assertTrue(sc.checkExpression());

        sc.setExpression("<breakfast_menu><food><name>(%Belgian Waffles%)</name><price>{$5.95}</price><description>Two Belgian Waffles with maple syrup</description><calories>650</calories></food></breakfast_menu>");
        assertTrue(sc.checkExpression());

        sc.setExpression("<?xml version=\"1.0\" encoding=\"UTF-8\"?><data>{Invoice}<value=108898</data>");
        assertFalse(sc.checkExpression());

        sc.setExpression("<meta charset=\"UTF-8\"><meta name={\"viewport\"} content=\"width=[device-width] [initial-scale=1]\"><title>Today's Date</title>");
        assertTrue(sc.checkExpression());
    }
}
