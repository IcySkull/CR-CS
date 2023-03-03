import static org.junit.Assert.assertEquals;

import java.io.*;
import java.util.*;

import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class GraphAdjTests {
    private final String inputFileName;
    String workingDir = GraphAdjMatrix.class.getResource("").getPath()  + ".." + File.separator;

    GraphAdjList actualList = new GraphAdjList();
    GraphAdjMatrix actualMatrix = new GraphAdjMatrix();
    ListTester listTester = new ListTester();
    MatrixTester matrixTester = new MatrixTester();

    public GraphAdjTests(String inputFileName) {
        this.inputFileName = inputFileName;
    }

    @Parameterized.Parameters(name = "{0}")
    public static Collection<Object[]> inputFiles() {
        return Arrays.asList(new Object[][] {
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
        actualList = new GraphAdjList();
        actualMatrix = new GraphAdjMatrix();
        listTester = new ListTester();
        matrixTester = new MatrixTester();

        Scanner sc = new Scanner(new File(workingDir + inputFileName));
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

    @Test
    public void testDegreeSequence() {
        assertEquals(listTester.degreeSequence(), actualList.degreeSequence());
        assertEquals(matrixTester.degreeSequence(), actualMatrix.degreeSequence());
    }

    @Test
    public void testGetDistance2() {
        for (int vertex = 0; vertex < matrixTester.getNumVertices(); vertex++) {
            testDistances(listTester.getDistance2(vertex), actualList.getDistance2(vertex));
            testDistances(matrixTester.getDistance2(vertex), actualMatrix.getDistance2(vertex));
        }
    }

    public void testDistances(List<Integer> expected, List<Integer> actual) {
        assertEquals(expected.size(), actual.size());
        Set<Integer> expectedSet = new HashSet<>(expected);
        Set<Integer> actualSet = new HashSet<>(actual);

        assertEquals(expectedSet, actualSet);
    }
}