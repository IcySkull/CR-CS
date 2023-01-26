//� A+ Computer Science  -  www.apluscompsci.com
//Name -  	Diego Rodrigues
//Date -	1/19/2023
//Class - 	Computer Science III
//Lab  -	Binary Search Tree

public interface Treeable
{
	public Object getValue();
	public Treeable getLeft();
	public Treeable getRight();
	public void setValue(Comparable value);
	public void setLeft(Treeable left);
	public void setRight(Treeable right);
}