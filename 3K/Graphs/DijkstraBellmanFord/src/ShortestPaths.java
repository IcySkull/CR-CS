import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class ShortestPaths {
    static String workingDir = ShortestPaths.class.getResource("").getPath() + "../";

    public static void shortestPaths(ArrayList<Integer>[] adj, ArrayList<Integer>[] cost, int s, long[] distance, int[] reachable, int[] shortest) {
        Arrays.fill(distance, Long.MAX_VALUE);
        Arrays.fill(reachable, 0);
        Arrays.fill(shortest, 1);

        distance[s] = 0;

        List<Diedge> edges = getEdges(adj, cost);

        for (int V = 0; V < adj.length; V++) {
            for (Diedge edge : edges) {
                if (relax(distance, edge))
                    if (V == adj.length - 1)
                        shortest[edge.to] = 0;
            }
        }

        reachable(adj, cost, s, reachable);

        Set<Integer> cycleVertices = negativeCycle(adj, cost, edges, distance);

        System.out.println("cycleVertices: " + cycleVertices);

        reachableToNegCycle(adj, cost, shortest, cycleVertices);
    }

    private static void reachable(ArrayList<Integer>[] adj, ArrayList<Integer>[] cost, int s, int[] reachable) {
        Stack<Integer> frontier = new Stack<>();

        frontier.push(s);

        while (!frontier.isEmpty()) {
            int vertex = frontier.pop();

            if (reachable[vertex] == 1) {
                continue;
            }

            reachable[vertex] = 1;

            for (int neighbor : adj[vertex])
                frontier.push(neighbor);
        }
    }

    private static void reachableToNegCycle(ArrayList<Integer> adj[], ArrayList<Integer> cost[], int[] shortest, Set<Integer> cycleVertices) {
        Stack<Integer> frontier = new Stack<>();
        Set<Integer> visited = new HashSet<>();

        while (!cycleVertices.isEmpty()) {
            int vertex = cycleVertices.iterator().next();
            frontier.push(vertex);

            while (!frontier.isEmpty()) {
                int upcoming = frontier.pop();

                cycleVertices.remove(upcoming);

                if (visited.contains(upcoming))
                    continue;
                
                visited.add(upcoming);

                shortest[upcoming] = 0;

                for (int neighbor : adj[upcoming])
                    frontier.push(neighbor);
            }
        }
    }

    private static Set<Integer> negativeCycle(ArrayList<Integer>[] adj, ArrayList<Integer>[] cost, List<Diedge> edges, long[] distVMinusOne) {
        Set<Integer> cycle = new HashSet<>();
        for (Diedge edge : edges) {
            if (relax(distVMinusOne, edge)) {
                cycle.add(edge.to);
            }
        }
        return cycle;
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

    private static boolean relax(long[] distance, Diedge edge) {
        if (distance[edge.from] == Long.MAX_VALUE) {
            return false;
        }
        if (distance[edge.to] > distance[edge.from] + edge.weight) {
            distance[edge.to] = distance[edge.from] + edge.weight;
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

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(workingDir + "mediumEWD.txt"));
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        ArrayList<Integer>[] adj = (ArrayList<Integer>[])new ArrayList[n];
        ArrayList<Integer>[] cost = (ArrayList<Integer>[])new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<Integer>();
            cost[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < m; i++) {
            int x, y, w;
            x = scanner.nextInt();
            y = scanner.nextInt();
            w = Integer.parseInt(scanner.next().substring(2));
            adj[x].add(y);
            cost[x].add(w);
        }
        int s = 77;
        long distance[] = new long[n];
        int reachable[] = new int[n];
        int shortest[] = new int[n];
        for (int i = 0; i < n; i++) {
            distance[i] = Long.MAX_VALUE;
            reachable[i] = 0;
            shortest[i] = 1;
        }
        shortestPaths(adj, cost, s, distance, reachable, shortest);
        for (int i = 0; i < n; i++) {
            if (reachable[i] == 0) {
                System.out.println('*');
            } else if (shortest[i] == 0) {
                System.out.println('-');
            } else {
                System.out.println(distance[i]);
            }
        }

        System.out.println(distance[88]);
    }

}

