import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.ListIterator;
import static java.lang.System.*;

public class IteratorReplacer
{
	private ArrayList<String> list;
	private String toRemove, replaceWith;

	public IteratorReplacer(String line, String rem, String rep)
	{
		toRemove = rem;
		replaceWith = rep;
		list = new ArrayList<>();
		Collections.addAll(list, line.split(" "));
		replace();
	}

	public void setEmAll(String line, String rem, String rep)
	{
	}

	public void replace()
	{
		ListIterator<String> strIt = list.listIterator();
		while (strIt.hasNext()) {
			if (strIt.next().equals(toRemove))
				strIt.set(replaceWith);
		}
	}

	public String toString()
	{
		return list.toString()+"\n\n";
	}
}