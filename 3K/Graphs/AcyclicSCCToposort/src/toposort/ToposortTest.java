package toposort;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author bryce
 */
public class ToposortTest {

    ArrayList<Integer>[] adj;

    void loadFile(String name) {
        try {
            Scanner scanner = new Scanner(new File(name));
            int n = scanner.nextInt();
            int m = scanner.nextInt();
            adj = (ArrayList<Integer>[]) new ArrayList[n];
            for (int i = 0; i < n; i++) {
                adj[i] = new ArrayList<Integer>();
            }
            for (int i = 0; i < m; i++) {
                int x, y;
                x = scanner.nextInt();
                y = scanner.nextInt();
                adj[x].add(y);
            }
        } catch (FileNotFoundException ex) {
        }
    }

    private static int acyclic(ArrayList<Integer>[] adj, List<Integer> order) {
        Set<Integer> visitedSet = new HashSet<>();
        for (int i = order.size() - 1; i >= 0; i--) {
            visitedSet.add(order.get(i));
            for (int item : adj[order.get(i)]) {
                if (!visitedSet.contains(item)) {
                    return 1;
                }
            }
        }
        return 0;
    }

    @Test
    public void testTiny() {
        loadFile("smallDag.txt");
        List<Integer> topoList = Toposort.toposort(adj);
        assertEquals(0, acyclic(adj, topoList));
    }

    @Test
    public void testTiny2() {
        loadFile("myDag.txt");
        List<Integer> topoList = Toposort.toposort(adj);
        assertEquals(0, acyclic(adj, topoList));
    }

    @Test
    public void testMedium() {
        loadFile("largeDag.txt");
        List<Integer> topoList = Toposort.toposort(adj);
        assertEquals(0, acyclic(adj, topoList));
    }

    @Test
    public void testLarge() {
        loadFile("large2Dag.txt");
        List<Integer> topoList = Toposort.toposort(adj);
        assertEquals(0, acyclic(adj, topoList));
    }
}
