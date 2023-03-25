import java.io.*;
import java.util.*;


public class ConnectedComponents {
    static int numberOfComponents(ArrayList<Integer>[] adjList) {
        Set<Integer> visited = new HashSet<>();
        int count = 0;

        for (int i = 0; i < adjList.length; i++) {
            if (!visited.contains(i)) {
                count++;
                explore(adjList, i, visited);
            }
        }

        return count;
    }

    static void explore(ArrayList<Integer>[] adjList, int x, Set<Integer> visited) {
        visited.add(x);
        for (int v : adjList[x]) {
            if (!visited.contains(v)) {
                explore(adjList, v, visited);
            }
        }
    }


    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("tinyG.txt"));
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
            adj[x].add(y);
            adj[y].add(x);
        }
    }
}

