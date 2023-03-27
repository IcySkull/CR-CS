import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class StronglyConnected {

    public static int numberOfStronglyConnectedComponents(ArrayList<Integer>[] adj) {
        boolean[] visited = new boolean[adj.length];
        Stack<Integer> order = traverse(adj, visited);       
        List<Integer>[] transposed = transposed(adj);        
        visited = new boolean[adj.length];
 
        int count = 0;

        while (!order.isEmpty()) {
            int v = order.pop();
            if (!visited[v]) {
                Stack<Integer> comp = new Stack<>();
                dfsPostOrder(transposed, v, visited, comp);
                count++;
            }
        }

        return count;
    }

    public static void dfsPostOrder(List<Integer>[] graph, int v, boolean[] visited, Stack<Integer> order) 
    {
        Stack<Integer> stack = new Stack<>();
        stack.push(v);
        
        while (!stack.isEmpty()) {
            int currentNode = stack.peek();
            visited[currentNode] = true;
            
            boolean allNeighborsVisited = true;
            
            for (int neighbor : graph[currentNode]) {
                if (!visited[neighbor]) {
                    stack.push(neighbor);
                    visited[neighbor] = true;
                    allNeighborsVisited = false;
                    break;
                }
            }
            
            if (allNeighborsVisited) {
                order.add(currentNode);
                stack.pop();
            }
        }
    }


    public static Stack<Integer> traverse(List<Integer>[] graph, boolean[] visited) 
    {        
        Stack<Integer> order = new Stack<Integer>();
 
        for (int i = 0; i < graph.length; i++)
            if (!visited[i])
                dfsPostOrder(graph, i, visited, order);
        return order;
    }


    public static List<Integer>[] transposed(List<Integer>[] graph)
    {
        int len = graph.length;
        List<Integer>[] transposed = new List[len];
        for (int i = 0; i < len; i++)
            transposed[i] = new ArrayList<Integer>();
        for (int v = 0; v < len; v++)
            for (int i = 0; i < graph[v].size(); i++)
                transposed[graph[v].get(i)].add(v);
        return transposed;
    }

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("mediumDG.txt"));
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        ArrayList<Integer>[] adj = (ArrayList<Integer>[]) new ArrayList[n];
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
