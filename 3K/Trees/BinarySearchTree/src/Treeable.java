//� A+ Computer Science  -  www.apluscompsci.com
//Name -  	Diego Rodrigues
//Date -	1/19/2023
//Class - 	Computer Science III
//Lab  -	Binary Search Tree

public interface Treeable<T extends Comparable<T>>
{
	public T getValue();
	public Treeable<T> getLeft();
	public Treeable<T> getRight();
	public void setValue(T value);
	public void setLeft(Treeable<T> left);
	public void setRight(Treeable<T> right);
}