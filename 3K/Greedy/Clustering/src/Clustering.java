import java.util.*;

public class Clustering {
    public static double clustering(int[] x, int[] y, int k) {
        PriorityQueue<Edge> edges = edges(x, y);
        WeightedQuickUnionPathCompressionUF uf = new WeightedQuickUnionPathCompressionUF(x.length);

        while (uf.count() > k) {
            Edge e = edges.poll();
            if (uf.find(e.u) != uf.find(e.v))
                uf.union(e.u, e.v);
        }

        Edge minD;
        do
            minD = edges.poll();
        while (uf.find(minD.u) == uf.find(minD.v));

        return minD.dist;
    }

    private static PriorityQueue<Edge> edges(int[] x, int[] y) {
        PriorityQueue<Edge> edges = new PriorityQueue<>();
        for (int i = 0; i < x.length; i++)
            for (int j = i + 1; j < x.length; j++) 
                edges.add(new Edge(x[i], y[i], x[j], y[j], i, j));
        return edges;
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
        int k = scanner.nextInt();
        System.out.println(clustering(x, y, k));
    }
}

class Edge implements Comparable<Edge> {
    int u; // index of point u
    int v; // index of point v
    double dist;

    public Edge(int x1, int y1, int x2, int y2, int u, int v) {
        this.u = u;
        this.v = v;
        this.dist = Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
    }

    @Override
    public int compareTo(Edge o) {
        return Double.compare(this.dist, o.dist);
    }
}
