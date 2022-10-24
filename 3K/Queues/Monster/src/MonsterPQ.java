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
import java.util.Scanner;
import java.util.PriorityQueue;

public class MonsterPQ  
{
	Queue<Monster> pQueue;

	public MonsterPQ()
	{
		pQueue = new PriorityQueue<>();
	}

	public MonsterPQ(List<Monster> monsters)
	{
		setPQ(monsters);
	}

	public MonsterPQ(Queue<Monster> q) {
		pQueue = new PriorityQueue<>(q);
	}

	public void setPQ(List<Monster> monsters)
	{
		pQueue = new PriorityQueue<>(monsters);
	}
	
	public Monster getMin()
	{
		Optional<Monster> min = pQueue.stream().min(Monster::compareTo);
		if (!min.isPresent())
			throw new IllegalArgumentException("No min value was found");
		return min.get();
	}

	public boolean isEmpty() {
		return pQueue.size() == 0;
	}

	public boolean remove(Monster monster) {
		return pQueue.remove(monster);
	}
	
	public String getNaturalOrder()
	{
		String output="";
		MonsterPQ copyQueue = new MonsterPQ(pQueue);
		while (!copyQueue.isEmpty()) {
			Monster min = copyQueue.getMin();
			copyQueue.remove(min);
			output += min + " ";
		}
		return output.trim();		
	}

	public Monster removeMin() {
		Monster min = getMin();
		pQueue.remove(min);
		return min;
	}

	@Override
	public String toString() {
		return pQueue.toString();
	}
}