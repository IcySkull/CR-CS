//� A+ Computer Science  -  www.apluscompsci.com
//Name -
//Date -  
//Class - 
//Lab  -

import static java.lang.System.*;

public class ListFunHouse
{
	//this method will print the entire list on the screen
   public static void print(ListNode list)
   {
	String out = "";
	ListNode itr = list;
	while (itr != null) {
		out += itr.getValue() + " ";
		itr = itr.getNext();
	}
	System.out.println(out);
   }		
	
   public static ListNode getNthNode(ListNode list, int n)
   {
	ListNode itr = list;
	while (n > 0 && itr != null) {
		itr = list.getNext();
		n--;
	}
	return itr;
   }

	//this method will return the number of nodes present in list
	public static int nodeCount(ListNode list)
	{
   	int count=0;
	ListNode itr = list;
	if (itr.getValue() == null)
		return 0;
	while (itr != null) {
		count++;
		itr = itr.getNext();
	}
	return count;
	}
		
	//this method will create a new node with the same value as the first node and add this
	//new node to the list.  Once finished, the first node will occur twice.
	public static void doubleFirst(ListNode list)
	{
		list.setNext(new ListNode(list.getValue(), list));
	}

	//this method will create a new node with the same value as the last node and add this
	//new node at the end.  Once finished, the last node will occur twice.
	public static void doubleLast(ListNode list) {
		list.setPrev(new ListNode(last.getValue(), null));
	}
		
	//method skipEveryOther will remove every other node
	public static void skipEveryOther(ListNode list)
	{
		list = new ListNode();
	}

	//this method will set the value of every xth node in the list
	public static void setXthNode(ListNode list, int x, Comparable value)
	{
		if (x < 0)
			return;
		ListNode itr = list;
		int i = 0;
		while (itr != null) {
			if ((i+1) % x == 0)
				itr.setValue(value);
			itr = itr.getNext();
			i++;
		}
	}	

	//this method will remove every xth node in the list
	public static void removeXthNode(ListNode list, int x)
	{
		if (x < 0)
			return;
		ListNode prev = null;
		ListNode itr = list;
		int i = 0;
		while (itr != null) {
			if ((i+1) % x == 0)
				remove(prev, itr);
			prev = itr;
			itr = itr.getNext();
			i++;
		}
	}		

	protected static void remove(ListNode prev, ListNode current) {
		if (prev == null) {
			current = current.getNext();
		} else {
			prev.setNext(current.getNext());
		}
	}

	protected static ListNode getLastNode(ListNode list) {
		ListNode itr = list;
		while (itr.getNext() != null) {
			itr = itr.getNext();
		}
		return itr;
	}
}