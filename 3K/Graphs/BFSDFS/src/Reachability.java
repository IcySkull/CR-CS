import java.io.*;
import java.util.*;

public class Reachability {

    static int reach(List<Integer>[] adjList, int x, int y) {
        return reach(adjList, x, y, new HashSet<>()) ? 1 : 0;
    }

    static boolean reach(List<Integer>[] adjList, int x, int y, Set<Integer> visited) {
        if (visited.contains(x))
            return false;
        if (x == y)
            return true;
        visited.add(x);

        return adjList[x].stream().anyMatch(v -> reach(adjList, v, y, visited));
    }


    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("tinyG.txt"));
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        List<Integer>[] adj = new List[n];
        double startTime = System.currentTimeMillis();
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<Integer>();
        }

        for (int i = 0; i < m; i++) {
            int x, y;
            x = scanner.nextInt();
            y = scanner.nextInt();
            adj[x].add(y);
            adj[y].add(x);
        }   
    }
}

