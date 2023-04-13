import java.util.*;

public class Bipartite {
    public static int bipartite(ArrayList<Integer>[] adj) {
        Stack<Integer> frontier = new Stack<>();
        Set<Integer> visited = new HashSet<>();
        Map<Integer, Boolean> colors = new HashMap<>();

        frontier.add(0);
        colors.put(0, true);

        while (!frontier.isEmpty()) {
            int current = frontier.pop();

            if (visited.contains(current))
                continue;

            visited.add(current);

            for (Integer neighbor : adj[current]) {
                if (colors.containsKey(neighbor) && colors.get(neighbor) == colors.get(current))
                    return 0;

                colors.put(neighbor, !colors.get(current));
                frontier.add(neighbor);
            }
        }


        return 1;
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
        System.out.println(bipartite(adj));
    }
}

