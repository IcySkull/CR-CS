package grafos;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import grafos.edges.Diedge;

public class AdjacencyList<V> extends Digraph<V> {
    Map<V, Set<Diedge<V>>> adjList;

    public AdjacencyList() {
        adjList = new HashMap<>();
    }

    public AdjacencyList(Map<V, Set<Diedge<V>>> adjList) {
        this.adjList = new HashMap<>(adjList);
    }

    public AdjacencyList(Digraph<V> graph) {
        this();
        for (V v : graph.vertices()) {
            addVertex(v);
            for (Diedge<V> e : graph.incidentEdges(v)) {
                addEdge(e);
            }
        }
    }

    public AdjacencyList(List<Integer>[] adj) {
        this();
        for (int i = 0; i < adj.length; i++)
            addVertex((V) Integer.valueOf(i));
        for (int i = 0; i < adj.length; i++) {
            for (int j = 0; j < adj[i].size(); j++)
                addEdge((V) Integer.valueOf(i), (V) adj[i].get(j));
        }
    }

    public <L> List<V> dfs(V start, V goal, Function<V, L> startLabeler) {
        return super.dfs(start, goal, startLabeler);
    }

    @Override
    public Collection<V> vertices() {
        return adjList.keySet();
    }

    @Override
    public Collection<Diedge<V>> edges() {
        return adjList.values().stream()
            .flatMap(Collection::stream)
            .collect(Collectors.toSet());
    }

    @Override
    public Collection<Diedge<V>> incidentEdges(V u) {
        return edges().stream()
            .filter(e -> e.head().equals(u))
            .collect(Collectors.toSet());
    }

    @Override
    public Collection<Diedge<V>> adjacentEdges(V u) {
        return adjList.get(u);
    }

    @Override
    public void addVertex(V v) {
        if (!adjList.containsKey(v))
            adjList.put(v, new HashSet<>());
    }

    @Override
    public void addEdge(Diedge<V> e) {
        if (!adjList.containsKey(e.tail()))
            throw new IllegalArgumentException("Start vertex: " + e.tail() + ", is not in the graph");
        else if (!adjList.containsKey(e.head()))
            throw new IllegalArgumentException("End vertex: " + e.head() + ", is not in the graph");
        else
            adjList.get(e.tail()).add(e);
    }

    public void addEdge(V tail, V head) {
        addEdge(new Diedge<>(tail, head));
    }
}
