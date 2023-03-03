import static org.junit.Assert.assertEquals;

import java.io.*;
import java.util.*;

import org.junit.*;

public abstract class GraphAdjTestsAbstract<I> {
    final I input;
    String workingDir = Graph.class.getResource("").getPath()  + ".." + File.separator;

    GraphAdjList actualList = new GraphAdjList();
    GraphAdjMatrix actualMatrix = new GraphAdjMatrix();

    ListTester listTester = new ListTester();
    MatrixTester matrixTester = new MatrixTester();

    public GraphAdjTestsAbstract() {
        input = null;
    }

    public GraphAdjTestsAbstract(I in) {
        this.input = in;
    }

    @Before
    public void setUp() throws FileNotFoundException {
        actualList = new GraphAdjList();
        actualMatrix = new GraphAdjMatrix();
        listTester = new ListTester();
        matrixTester = new MatrixTester();
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
