import java.util.Iterator;
public class Deque<Item> implements Iterable<Item> {
	Node first;
	private int size;

	private class Node{						// inner class for linked list(much easier than using an array)
		Item item;
		Node previous, next;

		public Node(Item item, Node previous, Node next){
			this.item = item;
			this.previous = previous;
			this.next = next;
		}
	}

	public Deque(){							 // construct an empty deque
		first = null;
		size = 0;
	}

    public boolean isEmpty(){				  // is the deque empty?
		return size() == 0;
    }
    public int size(){                        // return the number of items on the deque
		return size;
	}
   	public void addFirst(Item item){         // add the item to the front
		addInit(item);
		if (first == null) 
			addItself(item);
		else {
			Node oldFirst = first;
			first = new Node(item, oldFirst.previous, oldFirst);
			oldFirst.previous = first;
		}
   	}
   	public void addLast(Item item){           // add the item to the end
		addInit(item);
		if (first == null)
			addItself(item);
		else {
			Node inserted = new Node(item, first.previous, first);
			first.previous.next = inserted;
			first.previous = inserted;
		}
   	}

	private void addInit(Item item) {
		if (item == null)
			throw new java.util.NoSuchElementException();
		size++;
	} 

	private void addItself(Item item) {
		first = new Node(item, null, null);
		first.previous = first;
		first.next = first;
	}

    public Item removeFirst(){                // remove and return the item from the front
		removeInit();
		if (size == 0)
			return removeItself();
		first = first.next;
		return first.item;	// just here to compile - you'll need to modify
    }

    public Item removeLast(){                // remove and return the item from the end
		removeInit();
		if (size == 0) 
			return removeItself();
		first.previous.previous = first;
		return first.item;	// just here to compile - you'll need to modify
    }

	private void removeInit() {
		if (size == 0)
			throw new java.util.NoSuchElementException();
		size--;
	}

	private Item removeItself() {
		Item item = first.item;
		first = null;
		return item;
	}

    public Iterator<Item> iterator(){         // return an iterator over items in order from front to end
		return new ListIterator();
    }

    private class ListIterator implements Iterator<Item>{
    	private Node current = first.previous;
		private boolean firstTime = true;

    	public boolean hasNext(){
			return current != null && (firstTime || current.next != first);
		}

		public Item next(){
			if(!hasNext())
				throw new java.util.NoSuchElementException();
			firstTime = false;
			current = current.next;
			return current.item;
		}
    }
    public static void main(String[] args){   // unit testing
		Deque<String> test = new Deque<String>();
		test.addFirst("Love");
		test.addFirst("I");
		test.addLast("Computer");
		test.addLast("Science");
		System.out.println(test.size());
		for(String item: test)
			System.out.println(item);
		test.removeFirst();
		test.removeLast();
		test.removeFirst();

		System.out.println("\n");
		for(String item: test)
			System.out.println(item);
		System.out.println("\n");
		test.removeLast();
		test.addLast("Iterators");
		test.addLast("Rock");
		for(String item: test)
			System.out.println(item);
    }
}
/*
4
I
Love
Computer
Science


Computer


Iterators
Rock
*/