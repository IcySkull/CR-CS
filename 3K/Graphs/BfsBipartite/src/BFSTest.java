
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
public class BFSTest {

    String workingDir = getClass().getResource(".").getPath() + "../";
    ArrayList<Integer>[] adj;

    void loadFile(String name) {
        try {
            Scanner scanner = new Scanner(new File(workingDir + name));
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
                adj[y].add(x);
            }
            //System.out.println(BFS.distance(adj, x, y));
        } catch (FileNotFoundException ex) {
        }
    }

    @Test
    public void testTiny() {
        loadFile("tinyG.txt");
        assertEquals(2, BFS.distance(adj, 0, 4));
        assertEquals(2, BFS.distance(adj, 2, 6));
        assertEquals(1, BFS.distance(adj, 3, 5));
        assertEquals(1, BFS.distance(adj, 7, 8));
        assertEquals(1, BFS.distance(adj, 9, 12));
        assertEquals(2, BFS.distance(adj, 10, 11));
        assertEquals(-1, BFS.distance(adj, 0, 9));
        assertEquals(-1, BFS.distance(adj, 2, 7));
        assertEquals(-1, BFS.distance(adj, 8, 11));
        assertEquals(-1, BFS.distance(adj, 4, 12));
        assertEquals(3, BFS.distance(adj, 4, 2));
    }

    @Test
    public void testMedium() {
        loadFile("mediumG.txt");
        assertEquals(-1, BFS.distance(adj, 0, 250));
        assertEquals(-1, BFS.distance(adj, 10, 251));
        assertEquals(-1, BFS.distance(adj, 33, 252));
        assertEquals(-1, BFS.distance(adj, 156, 253));
        assertEquals(9, BFS.distance(adj, 0, 249));
        assertEquals(9, BFS.distance(adj, 1, 245));
        assertEquals(7, BFS.distance(adj, 0, 242));
        assertEquals(6, BFS.distance(adj, 0, 221));
        assertEquals(8, BFS.distance(adj, 0, 200));
        assertEquals(10, BFS.distance(adj, 123, 201));
        assertEquals(6, BFS.distance(adj, 3, 25));
        assertEquals(6, BFS.distance(adj, 62, 91));
        assertEquals(5, BFS.distance(adj, 4, 25));
        assertEquals(10, BFS.distance(adj, 10, 50));
        assertEquals(9, BFS.distance(adj, 44, 150));
        assertEquals(6, BFS.distance(adj, 77, 88));
    }

    @Test
    public void testLarge() {
        loadFile("largeG.txt");
        assertEquals(241, BFS.distance(adj, 0, 999999));
        assertEquals(329, BFS.distance(adj, 0, 25044));
        assertEquals(150, BFS.distance(adj, 1022, 26251));
        assertEquals(276, BFS.distance(adj, 33333, 25662));
        assertEquals(468, BFS.distance(adj, 156666, 253111));
        assertEquals(98, BFS.distance(adj, 964440, 249));
        assertEquals(133, BFS.distance(adj, 345, 245445));
        assertEquals(309, BFS.distance(adj, 4422, 43150));
    }

}
