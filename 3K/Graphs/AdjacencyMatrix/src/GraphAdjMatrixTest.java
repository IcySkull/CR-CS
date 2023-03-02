import static org.junit.Assert.assertEquals;

import java.io.*;
import java.util.*;

import org.junit.Test;

public class GraphAdjMatrixTest {
    public void loadGraphs(Graph graph1, Graph graph2, String fileName) throws FileNotFoundException {
        String workingDir = GraphAdjMatrix.class.getResource("").getPath() + "..";
        Scanner sc = new Scanner(new File(workingDir + "/graph1.txt"));
        int size = sc.nextInt();
        for (int i = 0; i<size; i++) {
            graph1.addVertex();
            graph2.addVertex();
        }
        while (sc.hasNextInt()) {
            int v = sc.nextInt();
            int n = sc.nextInt();
            graph1.addEdge(v, n);
            graph2.addEdge(v, n);
        }
    }

    public void assertSets(Set<Integer> expected, Set<Integer> actual) {
        assertEquals(expected.size(), actual.size());
        for (int i : expected) {
            assert actual.contains(i);
        }
    }

    @Test
    public void testDegreeSequenceGraph1() {
        GraphAdjMatrix matrixGraph = new GraphAdjMatrix();
        GraphAdjList listGraph = new GraphAdjList();
        try {
            loadGraphs(matrixGraph, listGraph, "graph1.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        List<Integer> expected = Arrays.asList(2,2,2,2,2,2,2,2,1,1);
        assertEquals(expected, matrixGraph.degreeSequence());
        assertEquals(expected, listGraph.degreeSequence());
    }

    @Test
    public void testGetDistance2Graph1() {
        GraphAdjMatrix matrixGraph = new GraphAdjMatrix();
        GraphAdjList listGraph = new GraphAdjList();
        try {
            loadGraphs(matrixGraph, listGraph, "graph1.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Set<Integer> expected = new HashSet<>(Arrays.asList(7));
        int nodeToTest = 5;
        assertSets(expected, new HashSet<>(matrixGraph.getDistance2(nodeToTest)));
        assertSets(expected, new HashSet<>(listGraph.getDistance2(nodeToTest)));
    }

}