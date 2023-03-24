import java.io.*;
import java.util.*;

import grafos.*;
import grafos.edges.*;
import grafos.traversal.*;
import grafos.traversal.GraphTraversal.*;
import grafos.visual.GraphViewer;

public class Reachability {
    static  int reach(List<Integer>[] adjList, int x, int y) {
        IntegerAdjList graph = new IntegerAdjList(adjList);
        return graph.reachable(x, y) ? 1 : 0;
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

        AdjacencyList<Integer> graph = new AdjacencyList<>(adj);

        GraphViewer<Integer, Diedge<Integer>> viewer = new GraphViewer<>(graph);
        viewer.run();


        System.out.println(graph.isBipartite());
    }
}

