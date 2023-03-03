import static org.junit.Assert.assertEquals;

import java.io.*;
import java.util.*;

import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class GraphAdjTestsTxt extends GraphAdjTestsAbstract<String> {

    public GraphAdjTestsTxt(String in) {
        super(in);
    }

    @Parameterized.Parameters(name = "{0}")
    public static Collection<String[]> inputFiles() {
        return Arrays.asList(new String[][] {
                { "graph1.txt" },
                { "graph2.txt" },
                { "graph3.txt" },
                { "graph4.txt" },
                { "graph5.txt" },
                { "graph6.txt" }
        });
    }

    @Before
    public void setUp() throws FileNotFoundException {
        super.setUp();
        Scanner sc = new Scanner(new File(workingDir + input));
        int size = sc.nextInt();

        for (int i = 0; i<size; i++) {
            actualList.addVertex();
            actualMatrix.addVertex();
            listTester.addVertex();
            matrixTester.addVertex();
        }

        while (sc.hasNextInt()) {
            int v = sc.nextInt();
            int n = sc.nextInt();
            actualList.addEdge(v, n);
            actualMatrix.addEdge(v, n);
            listTester.addEdge(v, n);
            matrixTester.addEdge(v, n);
        }
    }
}
