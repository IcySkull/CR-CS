import java.util.*;

import static java.lang.System.*;

public class IteratorRemover
{
	private ArrayList<String> list;
	private String toRemove;

	public IteratorRemover(String line, String rem)
	{
		list = new ArrayList<>();
		toRemove = rem;
		Collections.addAll(list, line.split(" "));
		remove();
	}

	public void setTest(String line, String rem) {

	}

	public void remove()
	{
		ListIterator<String> strIt = list.listIterator();
		while (strIt.hasNext()) {
			if (strIt.next().equals(toRemove))
				strIt.remove();
		}
	}

	public String toString()
	{
		String out = "";
		for (int i = 0; i < list.size(); i++) {
			if (i == 0)
				out += "[" + list.get(i) + ", ";
			else if (i == list.size()-1)
				out += list.get(i) + "]";
			else
				out += list.get(i) + ", ";
		}
		return out;
	}
}