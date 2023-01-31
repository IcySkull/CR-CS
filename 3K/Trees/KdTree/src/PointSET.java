
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;
import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class PointSET {
    private Set<Point2D> set;

    public PointSET() {                     // construct an empty set of points 
        set = new TreeSet<>();
    }

    public boolean isEmpty() {              // is the set empty? 
        return set.size() == 0;
    }

    public int size() {                      // number of points in the set 
        return set.size();
    }

    public void insert(Point2D p) {            // add the point to the set (if it is not already in the set)
        checkIfNull(p);
        set.add(p);
    }

    public boolean contains(Point2D p) {           // does the set contain point p? 
        checkIfNull(p);
        return set.contains(p);
    }

    public void draw() {                    // draw all points to standard draw with p.draw()
        StdDraw.setPenRadius(0.01);
        StdDraw.setPenColor(StdDraw.BLACK);
        set.stream().forEach(p -> p.draw());
    }

    public Iterable<Point2D> range(RectHV rect) {           // all points that are inside the rectangle 
        checkIfNull(rect);
        return set.stream().filter(p -> rect.contains(p)).toList();
    }

    public Point2D nearest(Point2D p) {           // a nearest neighbor in the set to point p; null if the set is empty 
        checkIfNull(p);
        return set.stream().min((p1, p2) -> Integer.compare(0, );
    }

    private void checkIfNull(Object o) {
        if (o == null) {
            throw new java.lang.NullPointerException();
        }
    }

    public static void main(String[] args) {                // unit testing of the methods (optional) 
        String filename = "circle10.txt";   // args[0];
        In in = new In(filename);

        StdDraw.enableDoubleBuffering();

        // initialize the data structures with N points from standard input
        PointSET brute = new PointSET();
        // KdTree kdtree = new KdTree();
        while (!in.isEmpty()) {
            double x = in.readDouble();
            double y = in.readDouble();
            Point2D p = new Point2D(x, y);
            // kdtree.insert(p);
            brute.insert(p);
        }
        brute.draw();
        StdDraw.show();
    }
}
