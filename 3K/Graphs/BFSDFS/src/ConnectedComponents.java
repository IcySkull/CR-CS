import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import grafos.AdjacencyList;
import grafos.edges.Diedge;
import grafos.visual.GraphViewer;

public class ConnectedComponents {
    static int numberOfComponents(AdjacencyList<Integer> graph) {
        return graph.components().size();
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
        
        AdjacencyList<Integer> graph = new AdjacencyList<>(adj);

        GraphViewer<Integer, Diedge<Integer>> viewer = new GraphViewer<>(graph);
        viewer.run();

    }
}

