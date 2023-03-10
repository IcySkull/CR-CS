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
    Map<Integer, Set<UnWeightedEdge>> adjMap;

    public AdjacencyList(List<Integer>[] adjList) {
        super();
        for (int vertex = 0; vertex < adjList.length; vertex++) {
            Set<UnWeightedEdge> incidentEdges = new HashSet<>();
            vertices.add(vertex);
            for (Integer w : adjList[vertex])
                incidentEdges.add(new UnWeightedEdge(vertex, w));
            adjMap.put(vertex, incidentEdges);
            edges.addAll(incidentEdges);
        }
        
        ver

    }

    class UnWeightedEdge extends AbstractGraph<Integer, W>.Edge {
        public UnWeightedEdge (Integer v, Integer w) {
            super(v, w);
        }

        
    }
} 

