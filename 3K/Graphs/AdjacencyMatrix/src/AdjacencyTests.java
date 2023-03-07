import static org.junit.Assert.assertEquals;

import java.io.*;
import java.util.*;

import org.junit.*;

public final class AdjacencyTests  {
    // workingDir is the directory where the input files are located note that the path is relative to the package
    // so if you are running the tests from the package, the path will be correct
    final String workingDir = Graph.class.getResource("").getPath()  + ".." + File.separator;

    final String[] listInput = new String[] {"graph1.txt", "graph2.txt", "graph3.txt", "graph4.txt", "graph5.txt", "graph6.txt"};

    // actualList and actualMatrix are the student's implementations
    GraphAdjList[] actualLists;
    GraphAdjMatrix[] actualMatrices;

    // ListTester and MatrixTester are the expected implementations of GraphAdjList and GraphAdjMatrix
    // that we are testing against actualList and actualMatrix which are student's implementations
    ListTester listTester = new ListTester();
    MatrixTester matrixTester = new MatrixTester();

    @Before
    public void setUp() throws FileNotFoundException {
        actualLists = new GraphAdjList[listInput.length];
        actualMatrices = new GraphAdjMatrix[listInput.length];
        listTester = new ListTester();
        matrixTester = new MatrixTester();
        for (String input : )
    }

    @Test
    public void testListDegreeSequenceInOrder() {
        inReverseNaturalOrder(actualList.degreeSequence());
    }

    @Test
    public void testMatrixDegreeSequenceInOrder() {
        inReverseNaturalOrder(actualMatrix.degreeSequence());
    }

    /**
     * Asserts that the list is in reverse natural order, i.e. the first element is greater than or equal to the second.
     * Helper method for InOrder tests.
     * @param list
     */
    public void inReverseNaturalOrder(List<Integer> list) {
        if (list.size() > 1) {
            for (int indexDegree = 0; indexDegree < list.size()-1; indexDegree++)
                assert list.get(indexDegree) >= list.get(indexDegree+1);
        }
    }

    @Test
    public void testListDegreeSequenceSameSize() {
        assertEquals(listTester.degreeSequence().size(), actualList.degreeSequence().size());
    }

    @Test
    public void testMatrixDegreeSequenceSameSize() {
        assertEquals(matrixTester.degreeSequence().size(), actualMatrix.degreeSequence().size());
    }

    @Test
    public void testListDegreeSequenceEquality() {
        assertEquals(listTester.degreeSequence(), actualList.degreeSequence());
    }

    @Test
    public void testMatrixDegreeSequenceEquality() {
        assertEquals(matrixTester.degreeSequence(), actualMatrix.degreeSequence());
    }

    @Test
    public void testListGetDistance2IsSet() {
        for (int vertex = 0; vertex < listTester.getNumVertices(); vertex++) {
            assertSet(actualList.getDistance2(vertex));
        }
    }

    @Test
    public void testMatrixGetDistance2IsSet() {
        for (int vertex = 0; vertex < matrixTester.getNumVertices(); vertex++) {
            assertSet(actualMatrix.getDistance2(vertex));
        }
    }

    /**
     * Asserts that the list is a set, i.e. that it has no duplicates. This is a helper method for IsSet tests.
     * @param actual
     */
    public void assertSet(List<Integer> actual) {
        Set<Integer> setExpected = new HashSet<>(actual);
        assertEquals(setExpected.size(), actual.size());
    }

    @Test
    public void testListGetDistance2SameSize() {
        for (int vertex = 0; vertex < listTester.getNumVertices(); vertex++) {
            assertEquals(listTester.getDistance2(vertex).size(), actualList.getDistance2(vertex).size());
        }
    }

    @Test
    public void testMatrixGetDistance2SameSize() {
        for (int vertex = 0; vertex < matrixTester.getNumVertices(); vertex++) {
            assertEquals(matrixTester.getDistance2(vertex).size(), actualMatrix.getDistance2(vertex).size());
        }
    }

    @Test
    public void testListGetDistance2Equality() {
        for (int vertex = 0; vertex < listTester.getNumVertices(); vertex++) {
            asserEqualityDistance(listTester.getDistance2(vertex), actualList.getDistance2(vertex));
        }
    }

    @Test
    public void testMatrixGetDistance2Equality() {
        for (int vertex = 0; vertex < matrixTester.getNumVertices(); vertex++) {
            asserEqualityDistance(matrixTester.getDistance2(vertex), actualMatrix.getDistance2(vertex));
        }
    }

    /**
     * Asserts that the two lists are equal, i.e. that they have exactly the same elements. Helper method for getDistance2 equality tests.
     * Notice that Lists might have different orders, but the same elements, so we convert them to Sets and compare them.
     * @param expected
     * @param actual
     */
    public void asserEqualityDistance(List<Integer> expected, List<Integer> actual) {
        Set<Integer> setExpected = new HashSet<>(expected);
        Set<Integer> setActual = new HashSet<>(actual);
        assertEquals(setExpected, setActual);
    }
}
