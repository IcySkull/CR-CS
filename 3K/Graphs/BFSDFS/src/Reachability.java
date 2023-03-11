
import java.util.*;

public class Reachability {
    static int reach(ArrayList<Integer>[] adj, int x, int y) {
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

class AdjacencyList<W extends Number> extends AbstractGraph<Integer, W> {
    Map<Integer, Set<UnweightedEdge>> adjMap;

    public AdjacencyList(List<Integer>[] adjList) {
        super();
<<<<<<< HEAD
        adjMap = new HashMap<>();

        Set<Integer> vertices = getVertices();
        Set<Edge> edges = getEdges();
        for (int i = 0; i < adjList.length; i++) {
            vertices.add(i);
            for (Integer j : adjList[i]) {
                edges.add(new UnweightedEdge(i, j));
            }

        }
=======
        
>>>>>>> fe7b3004728a0fd4fef94962797ad348b5be3b0d
    }

    class UnweightedEdge extends Edge {
        public UnweightedEdge (Integer v, Integer w) {
            super(v, w);
        }

        
    }
} 

