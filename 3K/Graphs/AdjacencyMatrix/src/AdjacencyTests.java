import static org.junit.Assert.assertEquals;

import java.io.*;
import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.stream.Collectors;

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
    ListTester[] listTester;
    MatrixTester[] matrixTester;

    @Before
    public void setUp() throws FileNotFoundException {
        actualLists = new GraphAdjList[listInput.length];
        actualMatrices = new GraphAdjMatrix[listInput.length];
        listTester = new ListTester[listInput.length];
        matrixTester = new MatrixTester[listInput.length];
        for (String input : listInput) {
            Scanner sc = new Scanner(new File(workingDir + input));
            int size = sc.nextInt();
            int index = Arrays.asList(listInput).indexOf(input);
            actualLists[index] = new GraphAdjList();
            actualMatrices[index] = new GraphAdjMatrix();
            listTester[index] = new ListTester();
            matrixTester[index] = new MatrixTester();

            for (int i = 0; i<size; i++) {
                actualLists[index].addVertex();
                actualMatrices[index].addVertex();
                listTester[index].addVertex();
                matrixTester[index].addVertex();
            }

            while (sc.hasNextInt()) {
                int v = sc.nextInt();
                int n = sc.nextInt();
                actualLists[index].addEdge(v, n);
                actualMatrices[index].addEdge(v, n);
                listTester[index].addEdge(v, n);
                matrixTester[index].addEdge(v, n);
            }

            sc.close();
        }
    }

    @Test
    public void testListDegreeSequenceInOrder() {
        testProperty(graph -> inReverseNaturalOrder(graph.degreeSequence()), actualLists);
    }

    @Test
    public void testMatrixDegreeSequenceInOrder() {
        testProperty(graph -> inReverseNaturalOrder(graph.degreeSequence()), actualMatrices);
    }

    /**
     * Asserts that the list is in reverse natural order, i.e. the first element is greater than or equal to the second.
     * Helper method for InOrder tests.
     * @param list
     */
    public void inReverseNaturalOrder(List<Integer> list) {
        for (int indexDegree = 0; indexDegree < list.size() - 1; indexDegree++) {
            assert list.get(indexDegree) >= list.get(indexDegree + 1);
        }
    }

    @Test
    public void testListDegreeSequenceSameSize() {
        testExpected((tester, graph) -> assertEquals(tester.degreeSequence().size(), graph.degreeSequence().size()), actualLists, listTester);
    }

    @Test
    public void testMatrixDegreeSequenceSameSize() {
        testExpected((tester, graph) -> assertEquals(tester.degreeSequence().size(), graph.degreeSequence().size()), actualMatrices, matrixTester);
    }

    @Test
    public void testListDegreeSequenceEquality() {
        testExpected((tester, graph) -> assertEquals(tester.degreeSequence(), graph.degreeSequence()), actualLists, listTester);
    }

    @Test
    public void testMatrixDegreeSequenceEquality() {
        testExpected((tester, graph) -> assertEquals(tester.degreeSequence(), graph.degreeSequence()), actualMatrices, matrixTester);
    }

    @Test
    public void testListGetDistance2IsSet() {
        testVertexProperty((graph, vertex) -> assertSet(graph.getDistance2(vertex)), actualLists);
    }

    @Test
    public void testMatrixGetDistance2IsSet() {
        testVertexProperty((graph, vertex) -> assertSet(graph.getDistance2(vertex)), actualMatrices);
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
        testVertexExpected((tester, graph, vertex) -> assertEquals(tester.getDistance2(vertex).size(), graph.getDistance2(vertex).size()), actualLists, listTester);
    }

    @Test
    public void testMatrixGetDistance2SameSize() {
        testVertexExpected((tester, graph, vertex) -> assertEquals(tester.getDistance2(vertex).size(), graph.getDistance2(vertex).size()), actualMatrices, matrixTester);
    }

    @Test
    public void testListGetDistance2Equality() {
        testVertexExpected((tester, graph, vertex) -> assertEqualityDistance(tester.getDistance2(vertex), graph.getDistance2(vertex)), actualLists, listTester);
    }

    @Test
    public void testMatrixGetDistance2Equality() {
        testVertexExpected((tester, graph, vertex) -> assertEqualityDistance(tester.getDistance2(vertex), graph.getDistance2(vertex)), actualMatrices, matrixTester);
    }

    /**
     * Asserts that the two lists are equal, i.e. that they have exactly the same elements. Helper method for getDistance2 equality tests.
     * Notice that Lists might have different orders, but the same elements, so we convert them to Sets and compare them.
     * @param expected
     * @param actual
     */
    public void assertEqualityDistance(List<Integer> expected, List<Integer> actual) {
        Set<Integer> setExpected = new HashSet<>(expected);
        Set<Integer> setActual = new HashSet<>(actual);
        assertEquals(setExpected, setActual);
    }

    public void testProperty(Consumer<Graph> test, Graph[] graphs) {
        for (Graph graph : graphs) {
            test.accept(graph);
        }
    }

    public void testExpected(BiConsumer<Graph, Graph> test, Graph[] expected, Graph[] actual) {
        for (int testIndex = 0; testIndex < expected.length; testIndex++) {
            test.accept(expected[testIndex], actual[testIndex]);
        }
    }

    public void testVertexProperty(BiConsumer<Graph, Integer> test, Graph[] graphs) {
        for (int testIndex = 0; testIndex < graphs.length; testIndex++) {
            for (int vertex = 0; vertex < graphs[testIndex].getNumVertices(); vertex++) {
                test.accept(graphs[testIndex], vertex);
            }
        }
    }

    public void testVertexExpected(TriConsumer<Graph, Graph, Integer> test, Graph[] expected, Graph[] actual) {
        for (int testIndex = 0; testIndex < expected.length; testIndex++) {
            for (int vertex = 0; vertex < expected[testIndex].getNumVertices(); vertex++) {
                test.accept(expected[testIndex], actual[testIndex], vertex);
            }
        }
    }
}

interface TriConsumer<A, B, C> {
    void accept(A a, B b, C c);
}

class ListTester extends Graph {
    private Map<Integer,ArrayList<Integer>> adjListsMap;

	public ListTester() {
		adjListsMap = new HashMap<Integer,ArrayList<Integer>>();
	}

	/**
	 * Implement the abstract method for adding a vertex.
	 */
	public void implementAddVertex() {
		int v = getNumVertices();
		// System.out.println("Adding vertex "+v);
		ArrayList<Integer> neighbors = new ArrayList<Integer>();
		adjListsMap.put(v,  neighbors);
	}

	/**
	 * Implement the abstract method for adding an edge.
	 * @param v the index of the start point for the edge.
	 * @param w the index of the end point for the edge.
	 */
	public void implementAddEdge(int v, int w) {
		(adjListsMap.get(v)).add(w);
	}

	/**
	 * Implement the abstract method for finding all
	 * out-neighbors of a vertex.
	 * If there are multiple edges between the vertex
	 * and one of its out-neighbors, this neighbor
	 * appears once in the list for each of these edges.
	 *
	 * @param v the index of vertex.
	 * @return List<Integer> a list of indices of vertices.
	 */
	public List<Integer> getNeighbors(int v) {
		return new ArrayList<Integer>(adjListsMap.get(v));
	}

	/**
	 * Implement the abstract method for finding all
	 * in-neighbors of a vertex.
	 * If there are multiple edges from another vertex
	 * to this one, the neighbor
	 * appears once in the list for each of these edges.
	 *
	 * @param v the index of vertex.
	 * @return List<Integer> a list of indices of vertices.
	 */
	public List<Integer> getInNeighbors(int v) {
		List<Integer> inNeighbors = new ArrayList<Integer>();
		for (int u : adjListsMap.keySet()) {
			//iterate through all edges in u's adjacency list and
			//add u to the inNeighbor list of v whenever an edge
			//with startpoint u has endpoint v.
			for (int w : adjListsMap.get(u)) {
				if (v == w) {
					inNeighbors.add(u);
				}
			}
		}
		return inNeighbors;
	}


	/**
	 * Implement the abstract method for finding all
	 * vertices reachable by two hops from v.
	 * @param v the index of vertex.
	 * @return List<Integer> a list of indices of vertices.
	 */
	 public List<Integer> getDistance2(int v) {
		return getNeighbors(v)
			.stream()
			.map(this::getNeighbors)
			.flatMap(List::stream)
			.distinct()
			.collect(Collectors.toList()
		);
	}

	/**
	 * Generate string representation of adjacency list
	 * @return the String
	 */
	public String adjacencyString() {
		String s = "Adjacency list";
		s += " (size " + getNumVertices() + "+" + getNumEdges() + " integers):";

		for (int v : adjListsMap.keySet()) {
			s += "\n\t"+v+": ";
			for (int w : adjListsMap.get(v)) {
				s += w+", ";
			}
		}
		return s;
	}
}

class MatrixTester extends Graph {
    private final int defaultNumVertices = 5;
	private int[][] adjMatrix;

	/** Create a new empty Graph */
	public MatrixTester() {
		adjMatrix = new int[defaultNumVertices][defaultNumVertices];
	}

	/**
	 * Implement the abstract method for adding a vertex.
	 * If need to increase dimensions of matrix, double them
	 * to amortize cost.
	 */
	public void implementAddVertex() {
		int v = getNumVertices();
		if (v >= adjMatrix.length) {
			int[][] newAdjMatrix = new int[v*2][v*2];
			for (int i = 0; i < adjMatrix.length; i ++) {
				for (int j = 0; j < adjMatrix.length; j ++) {
					newAdjMatrix[i][j] = adjMatrix[i][j];
				}
			}
			adjMatrix = newAdjMatrix;
		}
		for (int i=0; i < adjMatrix[v].length; i++) {
			adjMatrix[v][i] = 0;
		}
	}

	/**
	 * Implement the abstract method for adding an edge.
	 * Allows for multiple edges between two points:
	 * the entry at row v, column w stores the number of such edges.
	 * @param v the index of the start point for the edge.
	 * @param w the index of the end point for the edge.
	 */
	public void implementAddEdge(int v, int w) {
		adjMatrix[v][w] += 1;
	}

	/**
	 * Implement the abstract method for finding all
	 * out-neighbors of a vertex.
	 * If there are multiple edges between the vertex
	 * and one of its out-neighbors, this neighbor
	 * appears once in the list for each of these edges.
	 *
	 * @param v the index of vertex.
	 * @return List<Integer> a list of indices of vertices.
	 */
	public List<Integer> getNeighbors(int v) {
		List<Integer> neighbors = new ArrayList<Integer>();
		for (int i = 0; i < getNumVertices(); i ++) {
			for (int j=0; j< adjMatrix[v][i]; j ++) {
				neighbors.add(i);
			}
		}
		return neighbors;
	}

	/**
	 * Implement the abstract method for finding all
	 * in-neighbors of a vertex.
	 * If there are multiple edges from another vertex
	 * to this one, the neighbor
	 * appears once in the list for each of these edges.
	 *
	 * @param v the index of vertex.
	 * @return List<Integer> a list of indices of vertices.
	 */
	public List<Integer> getInNeighbors(int v) {
		List<Integer> inNeighbors = new ArrayList<Integer>();
		for (int i = 0; i < getNumVertices(); i ++) {
			for (int j=0; j< adjMatrix[i][v]; j++) {
				inNeighbors.add(i);
			}
		}
		return inNeighbors;
	}

	/**
	 * Implement the abstract method for finding all
	 * vertices reachable by two hops from v.
	 * Use matrix multiplication or you may utilize the getNeighbors method
	 *
	 * @param v the index of vertex.
	 * @return List<Integer> a list of indices of vertices.
	 */
	public List<Integer> getDistance2(int v) {
		return getNeighbors(v)
			.stream()
			.map(this::getNeighbors)
			.flatMap(Collection::stream)
			.distinct()
			.collect(Collectors.toList()
		);	
	}

	/**
	 * Generate string representation of adjacency matrix
	 * @return the String
	 */
	public String adjacencyString() {
		int dim = adjMatrix.length;
		String s = "Adjacency matrix";
		s += " (size " + dim + "x" + dim + " = " + dim* dim + " integers):";
		for (int i = 0; i < dim; i ++) {
			s += "\n\t"+i+": ";
			for (int j = 0; j < adjMatrix[i].length; j++) {
			s += adjMatrix[i][j] + ", ";
			}
		}
		return s;
	}
}
