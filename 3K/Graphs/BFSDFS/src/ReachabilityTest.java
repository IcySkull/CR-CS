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
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import grafos.AdjacencyList;

import static org.junit.Assert.*;

/**
 *
 * @author bryce
 */
public class ReachabilityTest {
    String workingDir = getClass().getResource("").getPath() + "../";

    AdjacencyList adjList;

    void loadFile(String name) {
        try {
            Scanner scanner = new Scanner(new File(workingDir + name));
            int n = scanner.nextInt();
            int m = scanner.nextInt();
            List<Integer>[] adj= (ArrayList<Integer>[]) new ArrayList[n];
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

            adjList = new AdjacencyList<>(adj);
            //System.out.println(Reachability.reach(adj, x, y));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ReachabilityTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Test
    public void testTiny() {
        loadFile("tinyG.txt");
        assertEquals(1, Reachability.reach(adjList, 0, 4));
        assertEquals(1, Reachability.reach(adjList, 2, 6));
        assertEquals(1, Reachability.reach(adjList, 3, 5));
        assertEquals(1, Reachability.reach(adjList, 7, 8));
        assertEquals(1, Reachability.reach(adjList, 9, 12));
        assertEquals(1, Reachability.reach(adjList, 10, 11));
        assertEquals(0, Reachability.reach(adjList, 0, 9));
        assertEquals(0, Reachability.reach(adjList, 2, 7));
        assertEquals(0, Reachability.reach(adjList, 8, 11));
        assertEquals(0, Reachability.reach(adjList, 4, 12));
        assertEquals(0, Reachability.reach(adjList, 8, 5));
    }

    @Test
    public void testMedium() {
        loadFile("mediumG.txt");
        assertEquals(0, Reachability.reach(adjList, 0, 250));
        assertEquals(0, Reachability.reach(adjList, 10, 251));
        assertEquals(0, Reachability.reach(adjList, 33, 252));
        assertEquals(0, Reachability.reach(adjList, 156, 253));
        assertEquals(1, Reachability.reach(adjList, 0, 249));
        assertEquals(1, Reachability.reach(adjList, 1, 245));
        assertEquals(1, Reachability.reach(adjList, 0, 242));
        assertEquals(1, Reachability.reach(adjList, 0, 221));
        assertEquals(1, Reachability.reach(adjList, 0, 200));
        assertEquals(1, Reachability.reach(adjList, 123, 201));
        assertEquals(1, Reachability.reach(adjList, 3, 25));
        assertEquals(1, Reachability.reach(adjList, 62, 91));
        assertEquals(1, Reachability.reach(adjList, 4, 25));
        assertEquals(1, Reachability.reach(adjList, 10, 50));
        assertEquals(1, Reachability.reach(adjList, 44, 150));
        assertEquals(1, Reachability.reach(adjList, 77, 88));
    }

    @Test
    public void testLarge() {
        loadFile("largeG.txt");

        assertEquals(1, Reachability.reach(adjList, 0, 999999));
        assertEquals(1, Reachability.reach(adjList, 0, 25044));
        assertEquals(1, Reachability.reach(adjList, 1022, 26251));
        assertEquals(1, Reachability.reach(adjList, 33333, 25662));
        assertEquals(1, Reachability.reach(adjList, 156666, 253111));
        assertEquals(1, Reachability.reach(adjList, 964440, 249));
        assertEquals(1, Reachability.reach(adjList, 345, 245445));
        assertEquals(1, Reachability.reach(adjList, 454, 242));
        assertEquals(1, Reachability.reach(adjList, 33330, 25421));
        assertEquals(1, Reachability.reach(adjList, 35440, 345200));
        assertEquals(1, Reachability.reach(adjList, 123, 985201));
        assertEquals(1, Reachability.reach(adjList, 344, 33325));
        assertEquals(1, Reachability.reach(adjList, 11162, 11491));
        assertEquals(1, Reachability.reach(adjList, 144, 45425));
        assertEquals(1, Reachability.reach(adjList, 2210, 5330));
        assertEquals(1, Reachability.reach(adjList, 4422, 43150));
        assertEquals(1, Reachability.reach(adjList, 7722, 883));
    }
}
