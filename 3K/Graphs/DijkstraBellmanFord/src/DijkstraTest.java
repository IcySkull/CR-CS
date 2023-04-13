
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author bryce
 */
public class DijkstraTest {

    static String workingDir = DijkstraTest.class.getResource("").getPath() + "../";
    static Scanner in;
    static ArrayList<Integer>[] adj;
    static ArrayList<Integer>[] cost;

    static {
        try {
            in = new Scanner(new File(workingDir + "biDTestCases.txt"));
        } catch (FileNotFoundException ex) {
        }
    }

    static void next() {
        int n = in.nextInt();
        int m = in.nextInt();
        adj = (ArrayList<Integer>[]) new ArrayList[n];
        cost = (ArrayList<Integer>[]) new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
            cost[i] = new ArrayList<>();
        }
        for (int i = 0; i < m; i++) {
            int x, y, w;
            x = in.nextInt();
            y = in.nextInt();
            w = in.nextInt();
            adj[x - 1].add(y - 1);
            cost[x - 1].add(w);
        }
    }

    static void nextM() {
        int n = in.nextInt();
        int m = in.nextInt();
        adj = (ArrayList<Integer>[]) new ArrayList[n];
        cost = (ArrayList<Integer>[]) new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
            cost[i] = new ArrayList<>();
        }
        for (int i = 0; i < m; i++) {
            int x, y, w;
            x = in.nextInt();
            y = in.nextInt();
            w = Integer.parseInt(in.next().substring(2));
            adj[x].add(y);
            cost[x].add(w);
        }
    }

    @Test
    public void testDistance() {
        next();
        assertEquals(Dijkstra.distance(adj, cost, 1 - 1, 1 - 1), 0);
        assertEquals(Dijkstra.distance(adj, cost, 2 - 1, 2 - 1), 0);
        assertEquals(Dijkstra.distance(adj, cost, 1 - 1, 2 - 1), 1);
        assertEquals(Dijkstra.distance(adj, cost, 2 - 1, 1 - 1), -1);
    }

    @Test
    public void testDistance2() {
        next();
        assertEquals(Dijkstra.distance(adj, cost, 1 - 1, 1 - 1), 0);
        assertEquals(Dijkstra.distance(adj, cost, 1 - 1, 2 - 1), 667);
        assertEquals(Dijkstra.distance(adj, cost, 1 - 1, 3 - 1), 677);
        assertEquals(Dijkstra.distance(adj, cost, 1 - 1, 4 - 1), 700);
        assertEquals(Dijkstra.distance(adj, cost, 1 - 1, 5 - 1), 622);
        assertEquals(Dijkstra.distance(adj, cost, 2 - 1, 1 - 1), 118);
        assertEquals(Dijkstra.distance(adj, cost, 2 - 1, 2 - 1), 0);
        assertEquals(Dijkstra.distance(adj, cost, 2 - 1, 3 - 1), 325);
        assertEquals(Dijkstra.distance(adj, cost, 2 - 1, 4 - 1), 239);
        assertEquals(Dijkstra.distance(adj, cost, 2 - 1, 5 - 1), 11);
    }

    @Test
    public void testDistance3() {
        next();
        assertEquals(Dijkstra.distance(adj, cost, 1 - 1, 1 - 1), 0);
        assertEquals(Dijkstra.distance(adj, cost, 1 - 1, 2 - 1), 3);
        assertEquals(Dijkstra.distance(adj, cost, 1 - 1, 3 - 1), 2);
        assertEquals(Dijkstra.distance(adj, cost, 1 - 1, 4 - 1), 5);
        assertEquals(Dijkstra.distance(adj, cost, 1 - 1, 5 - 1), 6);
        assertEquals(Dijkstra.distance(adj, cost, 2 - 1, 1 - 1), -1);
        assertEquals(Dijkstra.distance(adj, cost, 2 - 1, 2 - 1), 0);
        assertEquals(Dijkstra.distance(adj, cost, 2 - 1, 3 - 1), 3);
        assertEquals(Dijkstra.distance(adj, cost, 2 - 1, 4 - 1), 2);
        assertEquals(Dijkstra.distance(adj, cost, 2 - 1, 5 - 1), 3);
        assertEquals(Dijkstra.distance(adj, cost, 3 - 1, 1 - 1), -1);
        assertEquals(Dijkstra.distance(adj, cost, 3 - 1, 2 - 1), 1);
        assertEquals(Dijkstra.distance(adj, cost, 3 - 1, 3 - 1), 0);
        assertEquals(Dijkstra.distance(adj, cost, 3 - 1, 4 - 1), 3);
        assertEquals(Dijkstra.distance(adj, cost, 3 - 1, 5 - 1), 4);
        assertEquals(Dijkstra.distance(adj, cost, 4 - 1, 1 - 1), -1);
        assertEquals(Dijkstra.distance(adj, cost, 4 - 1, 2 - 1), -1);
        assertEquals(Dijkstra.distance(adj, cost, 4 - 1, 3 - 1), -1);
        assertEquals(Dijkstra.distance(adj, cost, 4 - 1, 4 - 1), 0);
        assertEquals(Dijkstra.distance(adj, cost, 4 - 1, 5 - 1), -1);
        assertEquals(Dijkstra.distance(adj, cost, 5 - 1, 1 - 1), -1);
        assertEquals(Dijkstra.distance(adj, cost, 5 - 1, 2 - 1), -1);
        assertEquals(Dijkstra.distance(adj, cost, 5 - 1, 3 - 1), -1);
        assertEquals(Dijkstra.distance(adj, cost, 5 - 1, 4 - 1), 1);
        assertEquals(Dijkstra.distance(adj, cost, 5 - 1, 5 - 1), 0);
    }

    @Test
    public void testDistance4() {
        next();
        assertEquals(Dijkstra.distance(adj, cost, 1 - 1, 1 - 1), 0);
        assertEquals(Dijkstra.distance(adj, cost, 1 - 1, 2 - 1), 105);
        assertEquals(Dijkstra.distance(adj, cost, 1 - 1, 3 - 1), 26);
        assertEquals(Dijkstra.distance(adj, cost, 1 - 1, 4 - 1), 99);
        assertEquals(Dijkstra.distance(adj, cost, 1 - 1, 5 - 1), 38);
        assertEquals(Dijkstra.distance(adj, cost, 2 - 1, 1 - 1), 139);
        assertEquals(Dijkstra.distance(adj, cost, 2 - 1, 2 - 1), 0);
        assertEquals(Dijkstra.distance(adj, cost, 2 - 1, 3 - 1), 121);
        assertEquals(Dijkstra.distance(adj, cost, 2 - 1, 4 - 1), 29);
        assertEquals(Dijkstra.distance(adj, cost, 2 - 1, 5 - 1), 174);
        assertEquals(Dijkstra.distance(adj, cost, 2 - 1, 7 - 1), 81);
        assertEquals(Dijkstra.distance(adj, cost, 3 - 1, 1 - 1), 183);
        assertEquals(Dijkstra.distance(adj, cost, 3 - 1, 2 - 1), 94);
        assertEquals(Dijkstra.distance(adj, cost, 3 - 1, 3 - 1), 0);
        assertEquals(Dijkstra.distance(adj, cost, 3 - 1, 4 - 1), 73);
        assertEquals(Dijkstra.distance(adj, cost, 3 - 1, 5 - 1), 97);
        assertEquals(Dijkstra.distance(adj, cost, 3 - 1, 8 - 1), 34);
        assertEquals(Dijkstra.distance(adj, cost, 4 - 1, 1 - 1), 110);
        assertEquals(Dijkstra.distance(adj, cost, 4 - 1, 2 - 1), 186);
        assertEquals(Dijkstra.distance(adj, cost, 4 - 1, 3 - 1), 92);
        assertEquals(Dijkstra.distance(adj, cost, 4 - 1, 4 - 1), 0);
        assertEquals(Dijkstra.distance(adj, cost, 4 - 1, 5 - 1), 145);
        assertEquals(Dijkstra.distance(adj, cost, 4 - 1, 7 - 1), 52);
        assertEquals(Dijkstra.distance(adj, cost, 5 - 1, 1 - 1), 186);
        assertEquals(Dijkstra.distance(adj, cost, 5 - 1, 2 - 1), 67);
        assertEquals(Dijkstra.distance(adj, cost, 5 - 1, 3 - 1), 168);
        assertEquals(Dijkstra.distance(adj, cost, 5 - 1, 4 - 1), 76);
        assertEquals(Dijkstra.distance(adj, cost, 5 - 1, 5 - 1), 0);
        assertEquals(Dijkstra.distance(adj, cost, 5 - 1, 6 - 1), 35);
        assertEquals(Dijkstra.distance(adj, cost, 6 - 1, 2 - 1), 32);
        assertEquals(Dijkstra.distance(adj, cost, 6 - 1, 7 - 1), 113);
        assertEquals(Dijkstra.distance(adj, cost, 8 - 1, 4 - 1), 39);
        assertEquals(Dijkstra.distance(adj, cost, 3 - 1, 8 - 1), 34);
    }

    @Test
    public void testDistance5() {
        try {
            in = new Scanner(new File(workingDir + "mediumEWD.txt"));
        } catch (FileNotFoundException ex) {
        }
        nextM();
        assertEquals(Dijkstra.distance(adj, cost, 3, 240), 35053);
        assertEquals(Dijkstra.distance(adj, cost, 55, 188), 27329);
        assertEquals(Dijkstra.distance(adj, cost, 3, 33), 63012);
        assertEquals(Dijkstra.distance(adj, cost, 154, 157), 29050);
        assertEquals(Dijkstra.distance(adj, cost, 43, 4), 60362);
        assertEquals(Dijkstra.distance(adj, cost, 1, 249), 12715);
        assertEquals(Dijkstra.distance(adj, cost, 77, 88), 51104);
        assertEquals(Dijkstra.distance(adj, cost, 99, 109), 43319);
    }

    @Test
    public void testDistance6() {
        try {
            in = new Scanner(new File(workingDir + "10000EWD.txt"));
        } catch (FileNotFoundException ex) {
        }
        nextM();
        assertEquals(Dijkstra.distance(adj, cost, 3, 240), 33425000);
        assertEquals(Dijkstra.distance(adj, cost, 55, 188), 126842000);
        assertEquals(Dijkstra.distance(adj, cost, 3, 33), 70486000);
        assertEquals(Dijkstra.distance(adj, cost, 154, 157), 75845000);
        assertEquals(Dijkstra.distance(adj, cost, 43, 4), 57350000);
        assertEquals(Dijkstra.distance(adj, cost, 1, 249), 42046000);
        assertEquals(Dijkstra.distance(adj, cost, 77, 88), 30921000);
        assertEquals(Dijkstra.distance(adj, cost, 1, 9988), 27089000);
        assertEquals(Dijkstra.distance(adj, cost, 265, 8438), 30406000);
    }

    @Test
    public void testDistance7() {
        try {
            in = new Scanner(new File(workingDir + "LargeEWD.txt"));
        } catch (FileNotFoundException ex) {
        }
        nextM();
        assertEquals(Dijkstra.distance(adj, cost, 3, 986342), 34275799);
        assertEquals(Dijkstra.distance(adj, cost, 55, 188), 37332196);
        assertEquals(Dijkstra.distance(adj, cost, 3, 33), 18076159);
        assertEquals(Dijkstra.distance(adj, cost, 154, 157), 91628330);
        assertEquals(Dijkstra.distance(adj, cost, 43, 4), 19614081);
        assertEquals(Dijkstra.distance(adj, cost, 1, 249), 20893566);
        assertEquals(Dijkstra.distance(adj, cost, 77, 88), 50874019);
        assertEquals(Dijkstra.distance(adj, cost, 1, 9988), 58640215);
        assertEquals(Dijkstra.distance(adj, cost, 265, 8438), 38595801);
    }
}
