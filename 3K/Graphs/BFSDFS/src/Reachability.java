import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class Reachability {
    static int reach(ArrayList<Integer>[] adj, int x, int y) {
        return bfs(adj, x, y);
    }


    static int bfs(ArrayList<Integer>[] adj, int x, int y) {
        List<Integer> queue = new ArrayList<>();
        boolean[] visited = new boolean[adj.length];
        queue.add(x);
        visited[x] = true;
        while (!queue.isEmpty()) {
            int v = queue.remove(0);
            if (v == y)
                return 1;
            for (int w : adj[v]) {
                if (!visited[w]) {
                    queue.add(w);
                    visited[w] = true;
                }
            }
        }
        return 0;
    }

    static int dfs(ArrayList<Integer>[] adj, int x, int y) {
        Stack<Integer> stack = new Stack<>();
        boolean[] visited = new boolean[adj.length];
        stack.add(x);
        visited[x] = true;
        while (!stack.isEmpty()) {
            int v = stack.pop();
            if (v == y)
                return 1;
            for (int w : adj[v]) {
                if (!visited[w]) {
                    stack.add(w);
                    visited[w] = true;
                }
            }
        }
        return 0;
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
        System.out.println(reach(adj, x, y));
    }
}

