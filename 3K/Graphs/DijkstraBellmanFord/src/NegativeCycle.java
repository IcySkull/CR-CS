import java.util.*;
import java.util.Scanner;

public class NegativeCycle {
    public static int negativeCycle(ArrayList<Integer>[] adj, ArrayList<Integer>[] cost) {

        int[] dist = new int[adj.length];

        Arrays.fill(dist, Integer.MAX_VALUE);

        dist[0] = 0;

        List<Diedge> edges = getEdges(adj, cost);

        for (int V = 0; V < adj.length; V++) {
            for (Diedge edge : edges) {
                if (relax(dist, edge))
                    if (V == adj.length-1)
                        return 1;
            }
        }

        return 0;
    }

    private static List<Diedge> getEdges(ArrayList<Integer>[] adj, ArrayList<Integer>[] cost) {
        List<Diedge> edges = new ArrayList<>();
        for (int vertex = 0; vertex < adj.length; vertex++) {
            for (int toIndex = 0; toIndex < adj[vertex].size(); toIndex++) {
                Diedge edge = getEdge(adj, cost, vertex, toIndex);
                edges.add(edge);
            }
        }
        return edges;
    }

    private static boolean relax(int[] dist, Diedge edge) {
        if (dist[edge.from] == Integer.MAX_VALUE) {
            return false;
        }
        if (dist[edge.to] > dist[edge.from] + edge.weight) {
            dist[edge.to] = dist[edge.from] + edge.weight;
            return true;
        }
        return false;
    }

    private static Diedge getEdge(ArrayList<Integer>[] adj, ArrayList<Integer>[] cost, int from, int toIndex) {
        return new Diedge(from, adj[from].get(toIndex), cost[from].get(toIndex));
    }

    static class Diedge {
        int from;
        int to;
        int weight;

        public Diedge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        ArrayList<Integer>[] adj = (ArrayList<Integer>[]) new ArrayList[n];
        ArrayList<Integer>[] cost = (ArrayList<Integer>[]) new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<Integer>();
            cost[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < m; i++) {
            int x, y, w;
            x = scanner.nextInt();
            y = scanner.nextInt();
            w = scanner.nextInt();
            adj[x - 1].add(y - 1);
            cost[x - 1].add(w);
        }
        System.out.println(negativeCycle(adj, cost));
    }
}
