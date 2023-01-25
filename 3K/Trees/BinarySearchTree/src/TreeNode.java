//� A+ Computer Science  -  www.apluscompsci.com
//Name -	Diego Rodrigues
//Date -	1/19/2023
//Class -   Computer Science III
//Lab  -	Binary Search Tree

public class TreeNode<T extends Comparable<T>> implements Treeable<T>
{
	private T treeNodeValue;
	private TreeNode<T> leftTreeNode;
	private TreeNode<T> rightTreeNode;

	public TreeNode( )
	{
		treeNodeValue = null;
		leftTreeNode = null;
		rightTreeNode = null;
	}

	public TreeNode(T value)
	{
		treeNodeValue = value;
		leftTreeNode = null;
		rightTreeNode = null;
	}

	public TreeNode(T value, TreeNode<T> left, TreeNode<T> right)
	{
		treeNodeValue = value;
		leftTreeNode = left;
		rightTreeNode = right;
	}

	public T getValue() {
		return treeNodeValue;
	}

	public Treeable<T> getLeft() {
		return leftTreeNode;
	}

	public Treeable<T> getRight() {
		return rightTreeNode;
	}

	public void setValue(T value) {
		treeNodeValue = value;
	}

	public void setLeft(Treeable<T> left) {
		leftTreeNode = (TreeNode<T>)left;
	}

	public void setRight(Treeable<T> right) {
		rightTreeNode = (TreeNode<T>)right;
	}

}