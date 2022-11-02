//� A+ Computer Science  -  www.apluscompsci.com
//Name -  
//Date -
//Class - 
//Lab  -

public class ListNode implements Linkable {
	private Comparable listNodeValue;
	private ListNode nextListNode;
	private ListNode prevListNode;

	public ListNode() {
		listNodeValue = null;
		prevListNode = null;
		nextListNode = null;
	}

	public ListNode(Comparable value) {
		listNodeValue = value;
		prevListNode = this;
		nextListNode = this;
	}

	public ListNode(Comparable value, ListNode prev, ListNode next) {
		listNodeValue = value;
		prevListNode = prev;
		nextListNode = next;
	}


	public void remove() {
		if (prevListNode == null)
			prevListNode.setNext(nextListNode);
		if (nextListNode != null)
			nextListNode.setPrev(prevListNode);
	}

	public Comparable getValue() {
		return listNodeValue;
	}

	public ListNode getNext() {
		return nextListNode;
	}

	public ListNode getPrev() {
		return prevListNode;
	}

	public void setValue(Comparable value) {
		listNodeValue = value;
	}

	public void setNext(Linkable next) {
		nextListNode = (ListNode) next;
	}

	public void setPrev(ListNode prevListNode) {
		this.prevListNode = prevListNode;
	}
}