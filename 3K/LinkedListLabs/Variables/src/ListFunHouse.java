//� A+ Computer Science  -  www.apluscompsci.com
//Name -
//Date -  
//Class - 
//Lab  -

import static java.lang.System.*;

public class ListFunHouse {

	public static void addToList(ListNode list, Comparable[] ray) {
		for (int i = 0; i < ray.length; i++) {
			ListFunHouse.addEnd(list, ray[i]);
		}
	}

	// this method will print the entire list on the screen
	public static void print(ListNode list) {
		String out = "";
		ListNode itr = list;
		do {
			out += itr.getValue() + " ";
			itr = itr.getNext();
		} while (itr != list);
		System.out.println(out);
	}

	// this method will return the number of nodes present in list
	public static int nodeCount(ListNode list) {
		int count = 0;
		ListNode itr = list;
		do {
			count++;
			itr = itr.getNext();
		} while (itr != list);
		return count;
	}

	// this method will create a new node with the same value as the first node and
	// add this
	// new node to the list. Once finished, the first node will occur twice.
	public static void doubleFirst(ListNode list) {
		list.setNext(new ListNode(list.getValue(), list, list.getNext()));
	}

	// this method will create a new node with the same value as the last node and
	// add this
	// new node at the end. Once finished, the last node will occur twice.
	public static void doubleLast(ListNode list) {
		ListFunHouse.addEnd(list, list.getValue());
	}

	// method skipEveryOther will remove every other node
	public static void skipEveryOther(ListNode list) {
		list = new ListNode();
	}

	// this method will set the value of every xth node in the list
	public static void setXthNode(ListNode list, int x, Comparable value) {
		if (x < 0)
			return;
		ListNode itr = list;
		int i = 0;
		do {
			if ((i + 1) % x == 0)
				itr.setValue(value);
			itr = itr.getNext();
			i++;
		} while (itr != list);
	}

	// this method will remove every xth node in the list
	public static void removeXthNode(ListNode list, int x) {
		if (x < 0)
			return;
		ListNode itr = list;
		int i = 0;
		do {
			if ((i + 1) % x == 0)
				remove(itr);
			itr = itr.getNext();
			i++;
		} while (itr != list);
	}

	protected static void remove(ListNode list) {
		if (list == null) {
			return;
		} else {
			if (list.getNext() == list) 
				list = new ListNode();
			else {
				list = list.getPrev();
				list.setNext(list.getNext().getNext());
			}
		}
	}

	protected static ListNode getLastNode(ListNode list) {
		ListNode itr = list;
		while (itr.getNext() != null) {
			itr = itr.getNext();
		}
		return itr;
	}

	public static void addEnd(ListNode list, Comparable value) {
		if (list.getPrev()== null) {
			list.setValue(value);
			list.setPrev(list);
			list.setNext(list);
		} else {
			ListNode inserted = new ListNode(value, list.getPrev(), list);
			list.getPrev().setNext(inserted);
			list.setPrev(inserted);
		}
	}

	public static void addFirst(ListNode list, Comparable value) {
		ListFunHouse.addEnd(list, value);
	}
}