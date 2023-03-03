import static org.junit.Assert.assertEquals;

import java.io.*;
import java.util.*;

import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class GraphAdjTests {
    private final String inputFileName;

    GraphAdjList actualList = new GraphAdjList();
    GraphAdjMatrix actualMatrix = new GraphAdjMatrix();
    GraphAdjListTester listTester = new GraphAdjListTester();
    GraphAdjMatrixTester matrixTester = new GraphAdjMatrixTester();

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

    public void loadGraphs(Graph graph1, Graph graph2, Graph graph3, Graph graph4, String fileName) throws FileNotFoundException {
        String workingDir = GraphAdjMatrix.class.getResource("").getPath() + "..";
        Scanner sc = new Scanner(new File(workingDir + "/graph1.txt"));
        int size = sc.nextInt();
        for (int i = 0; i<size; i++) {
            graph1.addVertex();
            graph2.addVertex();
            graph3.addVertex();
            graph4.addVertex();
        }
        while (sc.hasNextInt()) {
            int v = sc.nextInt();
            int n = sc.nextInt();
            graph1.addEdge(v, n);
            graph2.addEdge(v, n);
            graph3.addEdge(v, n);
            graph4.addEdge(v, n);
        }
    }

    @Before
    public void setUp() throws FileNotFoundException {
        actualList = new GraphAdjList();
        actualMatrix = new GraphAdjMatrix();
        listTester = new GraphAdjListTester();
        matrixTester = new GraphAdjMatrixTester();
        loadGraphs(actualList, actualMatrix, listTester, matrixTester, inputFileName);
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

/** A class that implements a directed graph. 
 * The graph may have self-loops, parallel edges. 
 * Vertices are labeled by integers 0 .. n-1
 * and may also have String labels.
 * The edges of the graph are not labeled.
 * Representation of edges via adjacency lists.
 * 
 * @author UCSD MOOC development team and YOU
 *
 */
class GraphAdjListTester extends Graph {


	private Map<Integer,ArrayList<Integer>> adjListsMap;
	
	/** 
	 * Create a new empty Graph
	 */
	public GraphAdjListTester() {
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
	 * 
	 * @param v the index of vertex.
	 * @return List<Integer> a list of indices of vertices.  
	 */		
	 public List<Integer> getDistance2(int v) {
		 
		 List<Integer> distance2 = new ArrayList<>();
		 
		 for( int u : getNeighbors(v)){
			 distance2.addAll(getNeighbors(u));
		 }
		 
		 return distance2;
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

/**
 * A class that implements a directed graph. The graph may have self-loops,
 * parallel edges. Vertices are labeled by integers 0 .. n-1 and may also have
 * String labels. The edges of the graph are not labeled. Representation of
 * edges via an adjacency matrix.
 * 
 * @author UCSD MOOC development team and YOU
 *
 */
class GraphAdjMatrixTester extends Graph {

	private final int defaultNumVertices = 5;
	private int[][] adjMatrix;

	/** Create a new empty Graph */
	public GraphAdjMatrixTester() {
		adjMatrix = new int[defaultNumVertices][defaultNumVertices];
	}

	/**
	 * Implement the abstract method for adding a vertex. If need to increase
	 * dimensions of matrix, double them to amortize cost.
	 */
	public void implementAddVertex() {
		int v = getNumVertices();
		if (v >= adjMatrix.length) {
			int[][] newAdjMatrix = new int[v * 2][v * 2];
			for (int i = 0; i < adjMatrix.length; i++) {
				for (int j = 0; j < adjMatrix.length; j++) {
					newAdjMatrix[i][j] = adjMatrix[i][j];
				}
			}
			adjMatrix = newAdjMatrix;
		}
		for (int i = 0; i < adjMatrix[v].length; i++) {
			adjMatrix[v][i] = 0;
		}
	}

	/**
	 * Implement the abstract method for adding an edge. Allows for multiple
	 * edges between two points: the entry at row v, column w stores the number
	 * of such edges.
	 * 
	 * @param v
	 *            the index of the start point for the edge.
	 * @param w
	 *            the index of the end point for the edge.
	 */
	public void implementAddEdge(int v, int w) {
		adjMatrix[v][w] += 1;
	}

	/**
	 * Implement the abstract method for finding all out-neighbors of a vertex.
	 * If there are multiple edges between the vertex and one of its
	 * out-neighbors, this neighbor appears once in the list for each of these
	 * edges.
	 * 
	 * @param v
	 *            the index of vertex.
	 * @return List<Integer> a list of indices of vertices.
	 */
	public List<Integer> getNeighbors(int v) {
		List<Integer> neighbors = new ArrayList<Integer>();
		for (int i = 0; i < getNumVertices(); i++) {
			for (int j = 0; j < adjMatrix[v][i]; j++) {
				neighbors.add(i);
			}
		}
		return neighbors;
	}

	/**
	 * Implement the abstract method for finding all in-neighbors of a vertex.
	 * If there are multiple edges from another vertex to this one, the neighbor
	 * appears once in the list for each of these edges.
	 * 
	 * @param v
	 *            the index of vertex.
	 * @return List<Integer> a list of indices of vertices.
	 */
	public List<Integer> getInNeighbors(int v) {
		List<Integer> inNeighbors = new ArrayList<Integer>();
		for (int i = 0; i < getNumVertices(); i++) {
			for (int j = 0; j < adjMatrix[i][v]; j++) {
				inNeighbors.add(i);
			}
		}
		return inNeighbors;
	}

	/**
	 * Implement the abstract method for finding all vertices reachable by two
	 * hops from v. Use matrix multiplication to record length 2 paths.
	 * 
	 * @param v
	 *            the index of vertex.
	 * @return List<Integer> a list of indices of vertices.
	 */
	public List<Integer> getDistance2(int v) {
		List<Integer> distance2 = new ArrayList<>();

		for (int u : getNeighbors(v)) {
			distance2.addAll(getNeighbors(u));
		}

		return distance2;
	}

	/**
	 * Generate string representation of adjacency matrix
	 * 
	 * @return the String
	 */
	public String adjacencyString() {
		int dim = adjMatrix.length;
		String s = "Adjacency matrix";
		s += " (size " + dim + "x" + dim + " = " + dim * dim + " integers):";
		for (int i = 0; i < dim; i++) {
			s += "\n\t" + i + ": ";
			for (int j = 0; j < adjMatrix[i].length; j++) {
				s += adjMatrix[i][j] + ", ";
			}
		}
		return s;
	}

}