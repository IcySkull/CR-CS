import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import static java.lang.System.*;

public class IteratorRemoverRunner
{
	public static void main ( String[] args )
	{
		IteratorRemover a = new IteratorRemover("a b c a b c a", "a");
		IteratorRemover b = new IteratorRemover("a b c d e f g h i j x x x x", "x");
		IteratorRemover c = new IteratorRemover("1 2 3 4 5 6 a b c a b c", "b");
		IteratorRemover d = new IteratorRemover("", "#");

		out.println(a);
		out.println(b);
		out.println(c);
		out.println(d);
	}
}