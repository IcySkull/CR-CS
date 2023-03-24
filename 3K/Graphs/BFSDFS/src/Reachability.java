import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import grafos.AbstractGraph;
import grafos.AdjacencyList;
import grafos.edges.AbstractEdge;
import grafos.edges.Diedge;
import grafos.traversal.GraphTraversal;
import grafos.traversal.UpcomingVertex;
import grafos.traversal.GraphTraversal.*;
import grafos.visual.GraphViewer;

public class Reachability {
    static  int reach(AdjacencyList<Integer> graph, int x, int y) {
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

