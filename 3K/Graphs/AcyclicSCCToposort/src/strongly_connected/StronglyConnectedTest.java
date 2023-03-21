package strongly_connected;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author bryce
 */
public class StronglyConnectedTest {

    ArrayList<Integer>[] adj;

    void loadFile(String name) {
        try {
            Scanner scanner = new Scanner(new File(name));
            int n = scanner.nextInt();
            int m = scanner.nextInt();
            adj = (ArrayList<Integer>[]) new ArrayList[n];
            for (int i = 0; i < n; i++) {
                adj[i] = new ArrayList<>();
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

    @Test
    public void testSmall() {
        loadFile("myDag.txt");
        assertEquals(4, StronglyConnected.numberOfStronglyConnectedComponents(adj));
    }

    @Test
    public void testMedium() {
        loadFile("mediumG.txt");
        assertEquals(250, StronglyConnected.numberOfStronglyConnectedComponents(adj));
    }

    @Test
    public void testMedium2() {
        loadFile("med2DG.txt");
        assertEquals(60, StronglyConnected.numberOfStronglyConnectedComponents(adj));
    }
    
    @Test
    public void testMassive() {
        loadFile("sccStupendous.txt");
        assertEquals(371762, StronglyConnected.numberOfStronglyConnectedComponents(adj));
    }
}