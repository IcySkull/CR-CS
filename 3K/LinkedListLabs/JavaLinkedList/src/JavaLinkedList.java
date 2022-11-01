//� A+ Computer Science  -  www.apluscompsci.com
//Name -
//Date -
//Class - 
//Lab  -

import java.util.LinkedList;
import java.util.NoSuchElementException;

import static java.lang.System.*;

public class JavaLinkedList
{
	private LinkedList<Integer> list;

	public JavaLinkedList()
	{
		list = new LinkedList<Integer>();
	}

	public JavaLinkedList(int[] nums)
	{
		list = new LinkedList<Integer>();
		for(int num : nums)
		{
			list.add(num);
		}
	}

	public double getSum()
	{
		double total = 0.0;
		LinkedList<Integer> copyList = getCopyOfList();
		while (!copyList.isEmpty()) {
			total += copyList.poll();
		}
		return total;
	}

	public double getAvg()
	{
		return getSum() / list.size();
	}

	public int getLargest()
	{
		if (list.isEmpty())
			throw new NoSuchElementException("largest should exist");
		LinkedList<Integer> copyList = getCopyOfList();
		int max = copyList.poll();
		while (!copyList.isEmpty()) {
			int c = copyList.poll();
			if (c > max)
				max = c;
		}
		return max;
	}

	public int getSmallest()
	{
		if (list.isEmpty())
			throw new NoSuchElementException("smallest should exist");
		LinkedList<Integer> copyList = getCopyOfList();
		int min = copyList.poll();
		while (!copyList.isEmpty()) {
			int c = copyList.poll();
			if (c < min)
				min = c;
		}
		return min;
	}

	public LinkedList<Integer> getCopyOfList() {
		return (LinkedList<Integer>)list.clone();
	}

	public String toString()
	{
		String output=list.toString() + "\n";
		output += "SUM:: " + getSum() + "\n";
		output += "AVERAGE:: " + getAvg() + "\n";
		output += "SMALLEST:: " + getSmallest() + "\n";
		output += "LARGEST:: " + getLargest();
		output += "\n";
		return output;
	}
}