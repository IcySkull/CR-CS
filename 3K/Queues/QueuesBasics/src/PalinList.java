//� A+ Computer Science  -  www.apluscompsci.com
//Name -
//Date -
//Class -  
//Lab  -

import java.util.Queue;
import java.util.Stack;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.management.QueryEval;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class PalinList
{
	private Queue<String> queue;
	private Stack<String> stack;

	public PalinList()
	{
		setList("");
	}

	public PalinList(String list)
	{
		setList(list);
	}

	public void setList(String list)
	{
		queue = new LinkedList<>(List.of(list.split(" ")));
		stack = new Stack<>();
		stack.addAll(List.of(list.split(" ")));
	}

	public boolean isPalin()
	{
		if (queue == null || stack == null)
			throw new IllegalArgumentException("No list found to compare");
		else if (queue.size() != stack.size())
			throw new IllegalStateException("Lists are not equal which might indicate an internal bug");
		return check();
	}

	public boolean check() {
		Queue<String> copQueue = new LinkedList<>(queue);
		Stack<String> copStack = new Stack<>();
		copStack.addAll(stack);
		while (copQueue.size() != 0) {
			if (!copQueue.poll().equals(stack.pop()))
				return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return queue.toString() + " is " + (isPalin() ? "" : "not ") + "a palinlist"; 
	}

}