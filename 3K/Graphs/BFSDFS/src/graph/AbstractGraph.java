package graph;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * A graph is a set of vertices and a set of edges. Each edge connects two
 * vertices. Note that AbstractGraph ignores if directed or undirected.
 * 
 */
public abstract class AbstractGraph<V, E extends Edge<V>> {
    private Set<V> vertices;
    private Set<E> edges;

    public AbstractGraph() {
        this.vertices = new HashSet<>();
        this.edges = new HashSet<>();
    }

    public AbstractGraph(Set<V> vertices, Set<E> edges) {
        this.vertices = vertices;
        this.edges = edges;
    }

    public void setVertices(Set<V> vertices) {
        this.vertices = vertices;
    }

    public Set<V> getVertices() {
        return vertices;
    }

    public Set<E> getEdges() {
        return edges;
    }

    public void addVertices(V... vs) {
        for (V v : vs)
            vertices.add(v);
    }

    public void addVertex(V v) {
        addVertex(v);
    }

    public void addEdges(E... es) {
        for (E e : es)
            edges.add(e);
    }

    public void addEdge(E e) {
        edges.add(e);
    }

    /**
     * Returns the number of vertices in the graph.
     */
    public int order() {
        return vertices.size();
    }

    /**
     * Returns the number of edges in the graph.
     */
    public int numEdges() {
        return edges.size();
    }

    /**
     * Returns the set of edges incident to vertex v.
     */
    public Set<E> incidentEdges(V v) {
        return edges.stream().filter(e -> e.isIncident(v)).collect(Collectors.toSet());
    }

    /**
     * Returns the set of edges adjacent to edge e.
     */
    public Set<E> adjacentEdges(E e) {
        return edges.stream().filter(e2 -> e2.isIncident(e.v()) && e2.isIncident(e.w())).collect(Collectors.toSet());
    }

    /**
     * Returns the set of vertices adjacent to vertex v.
     */
    public Set<V> adjacentVertices(V v) {
        return edges.stream().filter(e -> e.isIncident(v)).map(e -> e.adj(v)).collect(Collectors.toSet());
    }

    /**
     * Returns the degree of verte cx v.
     */
    public int degree(V v) {
        return incidentEdges(v).size();
    }

    public List<V> search(V start, V end, Collection<V> traverse, Function<Edge<V>, Number> heuristic) {
        if (!vertices.contains(start))
            throw new NullPointerException("Start vertex is not in the graph");
        if (!vertices.contains(end))
            throw new NullPointerException("End vertex is not in the graph");
        Map<V, V> parent = new HashMap<>();
        Map<V, Double> cost = new HashMap<>();
        traverse.add(start);
        cost.put(start, Double.valueOf(0));

        while (!traverse.isEmpty()) {
            V v = traverse.iterator().next();
            traverse.remove(v);

            if (v.equals(end))
                return backtrace(parent, end);

            for (Edge<V> e : incidentEdges(v)) {
                V w = e.adj(v);
                Double newCost = e.weight(cost.get(v));
                if (!cost.containsKey(w) || newCost.doubleValue() < cost.get(w).doubleValue()) {
                    parent.put(w, v);
                    cost.put(w, Double.valueOf(newCost.doubleValue() + heuristic.apply(e).doubleValue()));
                    traverse.add(w);
                }
            }
        }

        return null;
    }

    /*
     * Backtrace the path from start to end. Helper function for search.
     */
    public List<V> backtrace(Map<V, V> parent, V end) {
        List<V> path = new ArrayList<>();
        V v = end;
        while (v != null) {
            path.add(v);
            v = parent.get(v);
        }
        Collections.reverse(path);
        return path;
    }

    public List<V> bfs(V start, V end) {
        return search(start, end, (Queue) new LinkedList<>(), e -> 0);
    }

    public List<V> dfs(V start, V end) {
        return search(start, end, new Stack<>(), e -> 0);
    }

    public static void main(String[] args) {
        AbstractGraph<Integer, Edge<Integer>> g = new AbstractGraph<Integer,Edge<Integer>>() {};
        g.addVertices(1, 2, 3, 4);
        g.addEdge(new Edge<Integer>(1, 2) {});
        g.addEdge(new Edge<Integer>(3, 2) {});
        g.addEdge(new Edge<Integer>(4, 3) {});

        System.out.println(g.bfs(1, 4));
    }
}