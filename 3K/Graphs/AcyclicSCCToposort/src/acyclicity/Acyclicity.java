package acyclicity;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Acyclicity {
    static boolean acyclic(List<Integer>[] graph) {
        if (graph.length == 0)
            return false;

        Set<Integer> visited = new HashSet<>();
        Stack<Integer> stack = new Stack<>();
        
        stack.push(0);

        while (!stack.isEmpty()) {
            int current = stack.pop();

            if (visited.contains(current))
                return false;

            visited.add(current);

            for (int neighbor : graph[current])
                stack.push(neighbor);
        }
    
        return true;
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
        }
    }
}

