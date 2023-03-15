package graph;
import java.util.*;
import java.util.stream.Collectors;

public class AdjacencyList extends Digraph<Integer> {
    Map<Integer, Set<Integer>> adjList;

    public AdjacencyList() {
        adjList = new HashMap<>();
    }

    public AdjacencyList(int n) {
        this();
        for (int i = 0; i < n; i++)
            addVertex(i);
    }

    public AdjacencyList(List<Integer>[] adjList) {
        this(adjList.length);
        for (int i = 0; i < adjList.length; i++)
            for (int j : adjList[i])
                addEdge(new Diedge(i, j));
    }

    @Override
    public Set<Integer> getVertices() {
        return adjList.keySet();
    }

    @Override
    public Set<Digraph<Integer>.Diedge> getEdges() {
        return adjList.entrySet().stream()
                .flatMap(e -> e.getValue().stream()
                        .map(v -> new Diedge(e.getKey(), v)))
                .collect(Collectors.toSet());
    }

    @Override
    public void addVertex(Integer v) {
        adjList.putIfAbsent(v, new HashSet<>());
    }

    @Override
    public void addEdge(Diedge e) {
        if (!adjList.containsKey(e.tail()) || !adjList.containsKey(e.head()))
            throw new IllegalArgumentException("Invalid edge");
        adjList.get(e.tail()).add(e.head());
    }

    public void addEdge(int from, int to) {
        addEdge(new Diedge(from, to));
    }

    @Override
    public Set<Diedge> outEdges(Integer vertex) {
        return adjList.get(vertex).stream()
                .map(v -> new Diedge(vertex, v))
                .collect(Collectors.toSet());
    }

    public List<Integer> bfs(int from, int to) {
        return bfs(from, to, v -> 0);
    }

    public List<Integer> dfs(int from, int to) {
        return dfs(from, to, v -> 0);
    }
}
