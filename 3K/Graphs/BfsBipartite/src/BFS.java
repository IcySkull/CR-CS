import java.util.*;

public class BFS {
    public static int distance(ArrayList<Integer>[] adj, int s, int t) {
        Queue<Pair> frontier = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();

        frontier.add(new Pair(s, 0));

        while (!frontier.isEmpty()) {
            Pair current = frontier.poll();

            visited.add(current.vertex);

            if (current.vertex == t)
                return current.level;

            for (Integer neighbor : adj[current.vertex]) {
                if (visited.contains(neighbor))
                    continue;
                frontier.add(new Pair(neighbor, current.level + 1));
            }
        }
            
        return -1;
    }

    static class Pair {
        public final int vertex;
        public final int level;

        public Pair(int vertex, int level) {
            this.vertex = vertex;
            this.level = level;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        ArrayList<Integer>[] adj = (ArrayList<Integer>[])new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < m; i++) {
            int x, y;
            x = scanner.nextInt();
            y = scanner.nextInt();
            adj[x - 1].add(y - 1);
            adj[y - 1].add(x - 1);
        }
        int x = scanner.nextInt() - 1;
        int y = scanner.nextInt() - 1;
        System.out.println(distance(adj, x, y));
    }
}

