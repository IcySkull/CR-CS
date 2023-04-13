import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Dijkstra {
    static String workingDir = Dijkstra.class.getResource("").getPath() + "../";

    public static int distance(ArrayList<Integer>[] adj, ArrayList<Integer>[] cost, int s, int t) {
        PriorityQueue<Vertex> frontier = new PriorityQueue<Vertex>();
        Map<Integer, Integer> visited = new HashMap<>();
        
        frontier.add(new Vertex(s, 0));

        while (!frontier.isEmpty()) {
            Vertex current = frontier.poll();

            if (visited.containsKey(current.id))
                if (current.weight >= visited.get(current.id))
                    continue;
            
            if (current.id == t)
                return current.weight;

            visited.put(current.id, current.weight);
        
            for (int i = 0; i < adj[current.id].size(); i++) {
                int neighbor = adj[current.id].get(i);
                int weight = cost[current.id].get(i) + current.weight;
                frontier.add(new Vertex(neighbor, weight));
            }
        }

        return -1;
    }

    static class Vertex implements Comparable<Vertex> {
        final int id;
        final int weight;

        Vertex(int id, int weight) {
            this.id = id;
            this.weight = weight;
        }

        @Override
        public int compareTo(Vertex other) {
            return Integer.compare(weight, other.weight);
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
            cost[x ].add(w);
        }
        int x = scanner.nextInt() - 1;
        int y = scanner.nextInt() - 1;
        System.out.println(distance(adj, cost, x, y));
    }
}

