//� A+ Computer Science  -  www.apluscompsci.com
//Name -  
//Date -
//Class - 
//Lab  -

public class ListNode implements Linkable
{
	private Comparable listNodeValue;
	private ListNode nextListNode;
	private ListNode prevListNode;
	
	public ListNode()
	{
		listNodeValue = null;
		prevListNode = null;
		nextListNode = null;
	}

	public ListNode(Comparable value, ListNode next)
	{
		listNodeValue=value;
		prevListNode=null;
		nextListNode=next;
	}

	public ListNode(Comparable value, ListNode prev, ListNode next) {
		listNodeValue = value;
		prevListNode = null;
		nextListNode = null;
	}

	public Comparable getValue()
	{
		return listNodeValue;
	}

	public ListNode getNext()
	{
	   return nextListNode;
	}

	public ListNode getPrev() {
		return prevListNode;
	}

	public void setValue(Comparable value)
	{
		listNodeValue = value;
	}

	public void setNext(Linkable next)
	{
		nextListNode = (ListNode)next;
	}

	public void setPrev(ListNode prevListNode) {
		this.prevListNode = prevListNode;
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return super.equals(obj);
	}

	getj
}