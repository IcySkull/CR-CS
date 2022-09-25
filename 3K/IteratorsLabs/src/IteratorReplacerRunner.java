import java.util.ArrayList;
import java.util.Arrays;
import java.util.ListIterator;
import static java.lang.System.*;

public class IteratorReplacerRunner
{
	public static void main ( String[] args )
	{
		IteratorReplacer a = new IteratorReplacer("a b c a b c", "a", "+");
		IteratorReplacer b = new IteratorReplacer("a b c d e f g h i j x x x x", "x", "7");
		IteratorReplacer c = new IteratorReplacer("1 2 3 4 5 6 a b c a b c", "b", "#");
		IteratorReplacer d = new IteratorReplacer("", "7", "#");

		out.println(a);
		out.println(b);
		out.println(c);
		out.println(d);
	}
}