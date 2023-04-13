import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Set;

public class ConnectingPoints {
    public static double minimumDistance(int[] x, int[] y) {
        double result = 0.;
        PriorityQueue<Edge> edges = edges(x, y);

        Set<int[]> visited = new HashSet<>();
        while (visited.size() != x.length) {
            Edge next = edges.poll();
            result += next.dist;
            visited.add(new int[]{next.x1, next.y1});
            visited.add(new int[]{next.x2, next.y2});
        }
        return result;
    }

    private static List<Integer[]> graph(int[] x, int[] y) {
        List<Point2d>[] graph = new List[x.length];
        for (int x1 = 0; x1 < x.length; x1++)
            for (int x2 = x1 + 1; x2 < x.length; x2++) {
                List<Integer> vertex = new ArrayList<>();
                vertex.add()
                if (graph[x1] == null)
                    graph.[x1] = 
            }

        return graph;
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
    final double dist;

    public Edge(int x1, int y1, int x2, int y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;

        dist = Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
    }

    @Override
    public int compareTo(Edge o) {
        return Double.compare(this.dist, o.dist);
    }
}
