import java.util.*;

public class BinarySearchTree {
	private Treeable<Comparable> root;

	public BinarySearchTree() {
		root = null;
	}

	public void add(T val) {
		root = add(val, root);
	}

	private Treeable<Comparable> add(Comparable val, Treeable<Comparable> tree) {
		if (tree == null)
			tree = new TreeNode<Comparable>(val);

		T treeValue = tree.getValue();
		int valToTree = val.compareTo(treeValue);

		if (valToTree < 0)
			tree.setLeft(add(val, tree.getLeft()));
		else if (valToTree > 0)
			tree.setRight(add(val, tree.getRight()));

		return tree;
	}

	public void clear() {
		root = null;
	}

	public void inOrder() {
		inOrder(root);
		System.out.println("\n");
	}

	private void inOrder(Treeable<T> tree) {
		if (tree != null) {
			inOrder(tree.getLeft());
			System.out.print(tree.getValue() + " ");
			inOrder(tree.getRight());
		}
	}

	public void preOrder() {
		preOrder(root);
		System.out.println("\n");
	}

	private void preOrder(Treeable<T> tree) {
		if (tree != null) {
			System.out.print(tree.getValue() + " ");
			preOrder(tree.getLeft());
			preOrder(tree.getRight());
		}
	}

	public void postOrder() {
		postOrder(root);
		System.out.println("\n");
	}

	private void postOrder(Treeable<T> tree) {
		if (tree != null) {
			postOrder(tree.getLeft());
			postOrder(tree.getRight());
			System.out.print(tree.getValue() + " ");
		}
	}

	public void revOrder() {
		revOrder(root);
		System.out.println("\n");
	}

	private void revOrder(Treeable<T> tree) {
		if (tree != null) {
			revOrder(tree.getRight());
			System.out.print(tree.getValue() + " ");
			revOrder(tree.getLeft());
		}
	}

	public void levelOrder() {
		levelOrder(root);
		System.out.println("\n");
	}
	
	private void levelOrder(Treeable<T> tree) {
		Queue<Treeable<T>> queue = new LinkedList<>();
		queue.add(tree);
		while(!queue.isEmpty()) {
			Treeable<T> node = queue.poll();
			Treeable<T> left = node.getLeft();
			Treeable<T> right = node.getRight();
			System.out.print(node.getValue() + " ");
			if (left != null)
				queue.offer(left);
			if (right != null)
				queue.offer(right);
		}
	}

	public void zigzagOrder() {
		zigzagOrder(root);
		System.out.println("\n");
	}

	private void zigzagOrder(Treeable<T> tree) {
		Stack<Treeable<T>> stack1 = new Stack<>();
		Stack<Treeable<T>> stack2 = new Stack<>();
		stack1.add(tree);
		while (!(stack1.isEmpty() && stack2.isEmpty())) {
			while (!stack1.isEmpty()) {
				Treeable<T> node = stack1.pop();
				Treeable<T> left = node.getLeft();
				Treeable<T> right = node.getRight();
				System.out.print(node.getValue() + " ");
				if (left != null)
					stack2.push(left);
				if (right != null)
					stack2.push(right);
			}
			while (!stack2.isEmpty()) {
				Treeable<T> node = stack2.pop();
				Treeable<T> right = node.getRight();
				Treeable<T> left = node.getLeft();
				System.out.print(node.getValue() + " ");
				if (right != null)
					stack1.push(right);
				if (left != null)
					stack1.push(left);
			}
		}
	}

	public int getNumLevels() {
		return getNumLevels(root);
	}

	private int getNumLevels(Treeable<T> tree) {
		if (tree == null)
			return 0;
		int leftHeight = getNumLevels(tree.getLeft());
		int rightHeight = getNumLevels(tree.getRight());
		return Math.max(leftHeight, rightHeight) + 1;
	}

	public int getHeight() {
		return getHeight(root);
	}

	private int getHeight(Treeable<T> tree) {
		if (tree == null)
			return 0;
		return getNumLevels(tree) - 1;
	}
	
	public int getWidth() {
		return getWidth(root);
	}

	private int getWidth(Treeable<T> tree) {
		if (tree == null)
			return 0;
		int widthLeft= getWidth(tree.getLeft());
		int widthRight= getWidth(tree.getRight());
		int widthRoot = getNumLevels(tree.getLeft()) + getNumLevels(tree.getRight()) + 1;
		return Math.max(Math.max(widthRoot, widthLeft), Math.max(widthRoot, widthRight));
	}

	public int getNumNodes() {
		return getNumNodes(root);
	}

	private int getNumNodes(Treeable<T> tree) {
		if (tree == null)
			return 0;
		return 1 + getNumNodes(tree.getLeft()) + getNumNodes(tree.getRight());
	}

	public int getNumLeaves() {
		return getNumLeaves(root);
	}

	private int getNumLeaves(Treeable<T> tree) {
		if (tree == null)
			return 0;
		else if (tree.getLeft() == null && tree.getRight() == null)
			return 1;
		return getNumLeaves(tree.getLeft()) + getNumLeaves(tree.getRight());
	}

	// isFull
	public boolean isFull() {
		return (Math.pow(getNumLevels(), 2)) == getNumNodes();
	}

	// contains
	public boolean contains(T val) {
		return contains(val, root);
	}

	private boolean contains(T val, Treeable<T> tree) {
		return tree != null && 
			(tree.getValue().equals(val) || 
			contains(val, tree.getLeft()) || 
			contains(val, tree.getRight()));
	}

	// maxNode
	public T maxNode() {
		return getLargest(root);
	}

	// minNode
	public T minNode() {
		return getSmallest(root);
	}

	public T getSmallest() {
		return getSmallest(root);
	}

	// getSmallest
	public T getSmallest(Treeable<T> tree) {
		if (tree == null)
			return null;
		
		T nodeVal = tree.getValue();
		T leftVal = getSmallest(tree.getLeft());
		T rightVal = getSmallest(tree.getRight());

		if (leftVal != null && leftVal.compareTo(nodeVal) < 0)
			nodeVal = leftVal;
		if (rightVal != null && rightVal.compareTo(nodeVal) < 0)
			nodeVal = rightVal;
		
		return nodeVal;
	}

	public T getLargest() {
		return getLargest(root);
	}

	public T getLargest(Treeable<T> tree) {
		if (tree == null)
			return null;

		T nodeVal = tree.getValue();
		T leftVal = getLargest(tree.getLeft());
		T rightVal = getLargest(tree.getRight());

		if (leftVal != null && leftVal.compareTo(nodeVal) > 0)
			nodeVal = leftVal;
		if (rightVal != null && rightVal.compareTo(nodeVal) > 0)
			nodeVal = rightVal;

		return nodeVal;
	}

	// remove - follow adds set up very closely and check powerpoint if needed
	// 1st case = no children
	// 2nd case = one child
	// 3rd case two children
	public void remove(T val) {
		root = remove(val, root);
	}

	private Treeable<T> remove(T val, Treeable<T> tree) {
		if (tree == null)
			return null;
		if (val.compareTo(tree.getValue()) < 0)
			tree.setLeft(remove(val, tree.getLeft()));
		else if (val.compareTo(tree.getValue()) > 0)
			tree.setRight(remove(val, tree.getRight()));
		else {
			if (tree.getLeft() == null)
				return tree.getRight();
			else if (tree.getRight() == null)
				return tree.getLeft();
			tree.setValue(getSmallest(tree.getRight()));
			tree.setRight(remove(tree.getValue(), tree.getRight()));
		}
		return tree;
	}

	public String toString() {
		return toString(root);
	}

	private String toString(Treeable<T> tree) {
		String out = "";
		if (tree != null) {
			out += toString(tree.getLeft());
			out += tree.getValue() + " ";
			out += toString(tree.getRight());
		}
		return out;
	}
}