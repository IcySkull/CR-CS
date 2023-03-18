/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Test;

import grafos.AdjacencyList;

import static org.junit.Assert.*;

/**
 *
 * @author bryce
 */
public class ConnectedComponentsTest {
    String workingDir = getClass().getResource(".").getPath() + "../";

    AdjacencyList<Integer> graph;

    void loadFile(String name) {
        try {
            Scanner scanner = new Scanner(new File(workingDir + name));
            int n = scanner.nextInt();
            int m = scanner.nextInt();
            List<Integer>[] adj = new List[n];
            adj = (ArrayList<Integer>[]) new ArrayList[n];
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

            graph = new AdjacencyList<>(adj);

        } catch (FileNotFoundException ex) {
            Logger.getLogger(ReachabilityTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Test
    public void testTiny() {
        loadFile("tinyG.txt");
        assertEquals(3, ConnectedComponents.numberOfComponents(graph));
    }

    @Test
    public void testMedium() {
        loadFile("mediumG.txt");
        assertEquals(1, ConnectedComponents.numberOfComponents(graph));
    }

    @Test
    public void testMedium2() {
        loadFile("mediumGG.txt");
        assertEquals(6, ConnectedComponents.numberOfComponents(graph));
    }
}
