import java.util.*;

public class Toposort {
    public static List<Integer> toposort(ArrayList<Integer>[] adj) {
        boolean[] visited = new boolean[adj.length];
        ArrayList<Integer> traversal = new ArrayList<>();

        for (int i = 0; i < adj.length; i++) {
            if (!visited[i]) {
                dfsPostOrder(adj, i, visited, traversal);
            }
        }

        Collections.reverse(traversal);

        return traversal;
    }

    public static void dfsPostOrder(List<Integer>[] adj, int v, boolean[] visited, ArrayList<Integer> traversal) 
    {
        Stack<Integer> stack = new Stack<>();
        stack.push(v);
        
        while (!stack.isEmpty()) {
            int currentNode = stack.peek();
            visited[currentNode] = true;
            
            boolean allNeighborsVisited = true;
            
            for (int neighbor : adj[currentNode]) {
                if (!visited[neighbor]) {
                    stack.push(neighbor);
                    visited[neighbor] = true;
                    allNeighborsVisited = false;
                    break;
                }
            }
            
            if (allNeighborsVisited) {
                traversal.add(currentNode);
                stack.pop();
            }
        }
    }
}

