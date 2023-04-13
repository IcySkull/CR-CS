import java.util.*;
import java.awt.Point;
import java.awt.geom.Point2D;

public class ConnectingPoints {
    public static double minimumDistance(int[] x, int[] y) {
        double result = 0.;

        PriorityQueue<Edge> pq = new PriorityQueue<>();
        Set<Integer> visited = new HashSet<>();
        int xIndex = 0;

        do {
            visited.add(xIndex);
            for (int x2 = 0; x2 < x.length; x2++)
                if (!visited.contains(x2))
                    pq.add(new Edge(x[xIndex], y[xIndex], x[x2], y[x2]));

            Edge e = pq.poll();
            result += e.dist;

            for (int i = 0; i < x.length; i++)
                if (x[i] == e.x2 && y[i] == e.y2) {
                    xIndex = i;
                    break;
                }
            
        } while (visited.size() < x.length);

        return result;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] x = new int[n];
        int[] y = new int[n];
        for (int i = 0; i < n; i++) {
            x[i] = scanner.nextInt();
            y[i] = scanner.nextInt();
        }
        System.out.println(minimumDistance(x, y));
    }
}

class Edge implements Comparable<Edge> {
    int x1, y1;
    int x2, y2;
    double dist;

    public Edge(int x1, int y1, int x2, int y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.dist = Point2D.distance(x1, y1, x2, y2);
    }

    @Override
    public int compareTo(Edge o) {
        return Double.compare(this.dist, o.dist);
    }

}