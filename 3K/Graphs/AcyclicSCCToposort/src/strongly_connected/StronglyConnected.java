package strongly_connected;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import grafos.AdjacencyList;

public class StronglyConnected {
    static int numberOfStronglyConnectedComponents(AdjacencyList<Integer> graph) {
        return graph.stronglyConnectedComponents().size();
    }

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new java.io.File("mediumDG.txt"));
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

        AdjacencyList<Integer> graph = new AdjacencyList<>(adj);

        System.out.println(graph.stronglyConnectedComponents());
    }
}

