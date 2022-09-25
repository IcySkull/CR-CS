package uniquedupes;//� A+ Computer Science  -  www.apluscompsci.com
//Name -
//Date -
//Class -
//Lab  -

import static java.lang.System.*;
import java.util.*;

public class DupRunner
{
	public static void main( String args[] )
	{
		String list1 = "a b c d e f g h a b c d e f g h i j k";
		out.println(UniquesDupes.getUniques(list1));
		out.println(UniquesDupes.getDupes(list1));
	}
}