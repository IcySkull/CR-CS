
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;
import java.util.ArrayList;

public class KdTree {
    private Node root;
    private int size;

    /**
     * returns if the size is equal to zero
     *
     * @return
     */
    public boolean isEmpty() { // is the set empty?
        return size == 0;
    }

    /**
     * returns the current size of the tree
     *
     * @return
     */
    public int size() { // number of points in the set
        return size;
    }

    /**
     * inserts a new node if not in the treeSet already the rectangles will
     * encompass the whole area
     * and the point will lie inside
     *
     * @param p
     */
    public void insert(Point2D p) {
        checkIfNull(p);
        size++;
        root = insert(p, root, true);
        root.rect = new RectHV(0, 0, 1, 1);
    }

    /**
     * helper method that inserts a node recursively and updates the Rectangle with
     * it -
     * alternatively a Rectangle could be passed as a parameter to free up space in
     * the node class
     *
     * @param p
     * @param node
     * @param vertical vertical is current root and true being vertical line
     * @return
     */
    private Node insert(Point2D p, Node node, boolean vertical) {
        checkIfNull(p);
        if (node == null)
            return new Node(p);

        double nodeEntry;
        double pEntry;

        if (vertical) {
            nodeEntry = node.p.x();
            pEntry = p.x();
        } else {
            nodeEntry = node.p.y();
            pEntry = p.y();
        }

        if (node.p.equals(p) && nodeEntry == pEntry) {
            size--;
            return node;
        }

        int cmp = Double.compare(pEntry, nodeEntry); // inserted point compared to current node

        if (cmp < 0) {
            node.left = insert(p, node.left, !vertical);
            if (vertical)
                node.left.rect = new RectHV(node.rect.xmin(), node.rect.ymin(), node.p.x(), node.rect.ymax());
            else
                node.left.rect = new RectHV(node.rect.xmin(), node.rect.ymin(), node.rect.xmax(), node.p.y());
        }

        else {
            node.right = insert(p, node.right, !vertical);
            if (vertical)
                node.right.rect = new RectHV(node.p.x(), node.rect.ymin(), node.rect.xmax(), node.rect.ymax());
            else
                node.right.rect = new RectHV(node.rect.xmin(), node.p.y(), node.rect.xmax(), node.rect.ymax());
        }

        return node;
    }

    /**
     * recursively ascertains if a point is in the tree
     *
     * @param p
     * @return
     */
    public boolean contains(Point2D p) { // does the set contain point p?
        checkIfNull(p);
        return contains(p.x(), p.y(), root, true);
    }

    /**
     * helper method to recursively determine if a point is in the tree by checking
     * left or right
     * based on vertical == true for x points and vertical == false for y points
     *
     * @param x
     * @param y
     * @param node
     * @param vertical
     * @return
     */
    private boolean contains(double x, double y, Node node, boolean vertical) {
        if (node == null)
            return false;
        else if (node.p.x() == x && node.p.y() == y)
            return true;
        else if (vertical) {
            if (x < node.p.x())
                return contains(x, y, node.left, !vertical);
            else
                return contains(x, y, node.right, !vertical);
        } else {
            if (y < node.p.y())
                return contains(x, y, node.left, !vertical);
            else
                return contains(x, y, node.right, !vertical);
        }
    }

    /**
     * draws all points to standard draw
     */
    public void draw() {
        StdDraw.setPenRadius(0.01);
        draw(root, true);
    }

    /**
     * method recursively draws nodes and red vertical lines and blue horizontal
     * lines based on
     * vertical
     *
     * @param node
     * @param vertical - true draws vertical red line inside node's rectangle and
     *                 blue horizontal
     *                 line inside node's rectangle otherwise
     */
    private void draw(Node node, boolean vertical) {
        if (node == null)
            return;

        StdDraw.setPenRadius(0.010);
        StdDraw.setPenColor(StdDraw.BLACK);
        node.p.draw();
        StdDraw.setPenRadius(0.001);
        if (vertical) {
            StdDraw.setPenColor(StdDraw.RED);
            StdDraw.line(node.p.x(), node.rect.ymin(), node.p.x(), node.rect.ymax());
        } else {
            StdDraw.setPenColor(StdDraw.BLUE);
            StdDraw.line(node.rect.xmin(), node.p.y(), node.rect.xmax(), node.p.y());
        }

        draw(node.left, !vertical);
        draw(node.right, !vertical);
    }

    /**
     * returns a data structure of all points that are inside the rectangle
     *
     * @param rect
     * @return
     */
    public Iterable<Point2D> range(RectHV rect) {
        checkIfNull(rect);
        ArrayList<Point2D> list = new ArrayList<>();
        range(rect, root, list);
        return list;
    }

    /**
     * helper method to add points that are inside the given rectangle
     *
     * @param rect
     * @param node
     * @param list
     */
    private void range(RectHV rect, Node node, ArrayList<Point2D> list) {
        if (node == null)
            return;
        if (rect.contains(node.p))
            list.add(node.p);
        if (node.left != null && rect.intersects(node.left.rect))
            range(rect, node.left, list);
        if (node.right != null && rect.intersects(node.right.rect))
            range(rect, node.right, list);
    }

    /**
     * a nearest neighbor in the set to point p; null if the set is empty
     *
     * @param p
     * @return
     */
    public Point2D nearest(Point2D p) {
        checkIfNull(p);
        if (root == null)
            return null;
        return nearest(p, root, root.p, true);
    }

    /**
     * helper method to recursively calculate closest point in tree to p with
     * pruning
     *
     * @param query
     * @param node
     * @param vertical
     */
    private Point2D nearest(Point2D query, Node node, Point2D closestFound, boolean vertical) {
        if (node.p.distanceSquaredTo(query) < closestFound.distanceSquaredTo(query))
            closestFound = node.p;
        Node first = node.left;
        Node second = node.right;

        if (vertical) {
            if (query.x() < node.p.x()) {
                first = node.left;
                second = node.right;
            } else {
                first = node.right;
                second = node.left;
            }
        } else {
            if (query.y() < node.p.y()) {
                first = node.left;
                second = node.right;
            } else {
                first = node.right;
                second = node.left;
            }
        }

        closestFound = dictateMin(query, first, closestFound, vertical);
        closestFound = dictateMin(query, second, closestFound, vertical);
        return closestFound;
    }

    private Point2D dictateMin(Point2D query, Node node, Point2D closestFound, boolean vertical) {
        if (node == null)
            return closestFound;
        if (node.rect.distanceSquaredTo(query) < closestFound.distanceSquaredTo(query)) {
            return nearest(query, node, closestFound, !vertical);
        }
        return closestFound;
    }

    /**
     * throws an exception if a null reference is passed
     *
     * @param o
     */
    private void checkIfNull(Object o) {
        if (o == null) {
            throw new java.lang.IllegalArgumentException();
        }
    }

    public static void main(String[] args) { // unit testing of the methods (optional)
        String filename = "circle100.txt"; // args[0];
        In in = new In(filename);

        StdDraw.enableDoubleBuffering();

        // initialize the data structures with N points from standard input
        // PointSET brute = new PointSET();
        KdTree kdtree = new KdTree();
        while (!in.isEmpty()) {
            double x = in.readDouble();
            double y = in.readDouble();
            Point2D p = new Point2D(x, y);
            // kdtree.insert(p);
            kdtree.insert(p);
        }
        kdtree.draw();
        StdDraw.show();
    }

    /**
     * inner class that provides the functionality of a Node with references
     * left/right, a point and
     * a RectHV object that is optional
     */
    private class Node {

        Node left, right;
        Point2D p;
        RectHV rect;

        public Node(Point2D p) {
            this.p = p;
        }
    }
}
