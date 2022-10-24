//� A+ Computer Science  -  www.apluscompsci.com
//Name -
//Date -
//Class -
//Lab  -  

import java.util.Queue;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.PriorityQueue;

public class PQTester
{
	private Queue<String> pQueue;

	public PQTester()
	{
		pQueue = new PriorityQueue<>();
	}

	public PQTester(String list)
	{
		setPQ(list);
	}

	public PQTester(Queue q) {
		pQueue = new PriorityQueue<>(q);
	}

	public void setPQ(String list)
	{
		pQueue = new PriorityQueue<>(List.of(list.split(" ")));
	}
	
	public String getMin()
	{
		Optional<String> min = pQueue.stream().min(String::compareTo);
		if (!min.isPresent())
			throw new IllegalArgumentException("No min value was found");
		return min.get();
	}

	public boolean isEmpty() {
		return pQueue.size() == 0;
	}

	public boolean remove(String str) {
		return pQueue.remove(str);
	}
	
	public String getNaturalOrder()
	{
		String output="";
		PQTester copyQueue = new PQTester(pQueue);
		while (!copyQueue.isEmpty()) {
			String min = copyQueue.getMin();
			copyQueue.remove(min);
			output += min + " ";
		}
		return output.trim();		
	}

	@Override
	public String toString() {
		return pQueue.toString();
	}
}