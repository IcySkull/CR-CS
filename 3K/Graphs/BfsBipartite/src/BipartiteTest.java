
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author bryce
 */
public class BipartiteTest {

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
        } catch (FileNotFoundException ex) {
        }
    }

    @Test
    public void test1() {
        loadFile("smallBP.txt");
        assertEquals(1, Bipartite.bipartite(adj));
    }

    @Test
    public void test2() {
        loadFile("medBP.txt");
        assertEquals(1, Bipartite.bipartite(adj));
    }

    @Test
    public void test3() {
        loadFile("medBP2.txt");
        assertEquals(1, Bipartite.bipartite(adj));
    }

    @Test
    public void test4() {
        loadFile("medBPNot.txt");
        assertEquals(0, Bipartite.bipartite(adj));
    }
}
