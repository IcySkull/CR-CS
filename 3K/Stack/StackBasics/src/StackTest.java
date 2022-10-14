//� A+ Computer Science  -  www.apluscompsci.com
//Name -
//Date -  
//Class -
//Lab  -

import java.util.*;

public class StackTest
{
	private Stack<String> stack;

	public StackTest()
	{
		stack = new Stack<String>();
	}

	public StackTest(String line)
	{
		this();
		setStack(line);
	}
	
	public void setStack(String line)
	{
		stack.addAll(Arrays.asList(line.split(" ")));
	}

	public static String collectItr(Iterator<String> itr) {
		String out = "";
		while (itr.hasNext())
			out += itr.next() + " ";
		return out.trim();
	}

	public Iterator<String> popEmAll()
	{
		List<String> out = new ArrayList<>();
		while (!stack.isEmpty()) {
			out.add(stack.pop());
		}
		return out.iterator();
	}
}