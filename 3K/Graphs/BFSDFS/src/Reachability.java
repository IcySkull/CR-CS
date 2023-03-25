import java.io.*;
import java.util.*;

public class Reachability {

    static int reach(List<Integer>[] adjList, int x, int y) {
        boolean result = reach(adjList, x, y, new boolean[adjList.length], new Stack<Integer>());
        return result ? 1 : 0;
    }

    static boolean reach(
        List<Integer>[] adjList, 
        int start, 
        int goal, 
        boolean[] visited,
        Stack<Integer> frontier
    ) {
        Integer current = start;
        frontier.push(current);

        while (!frontier.isEmpty()) {
            current = frontier.pop();
            if (visited[current])
                continue;

            visited[current] = true;

            if (current == goal)
                return true;
            
            for (Integer neighbor : adjList[current])
                frontier.push(neighbor);
        }

        return false;
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

