//� A+ Computer Science  -  www.apluscompsci.com
//Name -	Diego Rodrigues
//Date -	1/19/2023
//Class -   Computer Science III
//Lab  -	Binary Search Tree

public class TreeNode implements Treeable
{
	private Comparable treeNodeValue;
	private TreeNode leftTreeNode;
	private TreeNode rightTreeNode;

	public TreeNode( )
	{
		treeNodeValue = null;
		leftTreeNode = null;
		rightTreeNode = null;
	}

	public TreeNode(Comparable value)
	{
		treeNodeValue = value;
		leftTreeNode = null;
		rightTreeNode = null;
	}

	public TreeNode(Comparable value, TreeNode left, TreeNode right)
	{
		treeNodeValue = value;
		leftTreeNode = left;
		rightTreeNode = right;
	}

	public Comparable getValue() {
		return treeNodeValue;
	}

	public Treeable getLeft() {
		return leftTreeNode;
	}

	public Treeable getRight() {
		return rightTreeNode;
	}

	public void setValue(Comparable value) {
		treeNodeValue = value;
	}

	public void setLeft(Treeable left) {
		leftTreeNode = (TreeNode)left;
	}

	public void setRight(Treeable right) {
		rightTreeNode = (TreeNode)right;
	}

}