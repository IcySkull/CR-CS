package graph;

import java.util.*;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import graph.SearchFunction.*;

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

    /**
     * Archetypal search function. It is a generic function that can be used to implement any
     * search algorithm.
     * @param <L>
     * @param start The starting vertex
     * @param goal The goal vertex
     * @param frontierSupplier A supplier of a collection that will be used to store upcoming vertices to explore,
     *                        e.g. a queue or a stack. The collection type is specially important since it will
     *                       determine the order in which the vertices are explored.
     * @param startLabeler A function that will be used to label the starting vertex, it aslo determines the type
     *                      of the {@code L} generic parameter.
     * @param searchFunction A functional interface how the upcoming vertices are explored.
     * @param searchUtils A utility class that contains useful functions for the search. 
     * @return A list of vertices that form a path from the starting vertex to the goal vertex.
     */
    public <L> List<V> searchPath(
        V start, 
        V goal,
        Supplier<Collection<V>> frontierSupplier, 
        Function<V, L> startLabeler,
        SearchFunction<V, E, L> searchFunction, 
        SearchUtils<V, E, L> searchUtils
    ) {
        Collection<V> frontier = frontierSupplier.get();
        Map<V, V> parent = new HashMap<>();
        Map<V, L> labels = new HashMap<>();

        frontier.add(start);
        labels.put(start, startLabeler.apply(start));

        while (!frontier.isEmpty()) {
            Iterator<V> it = frontier.iterator();
            V v = it.next();
            it.remove();

            for (E adjE: incidentEdges(v)) {
                if (labels.containsKey(adjE.adj(v)))
                    continue;
                SearchState<V, E, L> state = new SearchState<>(v, adjE, frontier, parent, labels);

                SearchResult<V, L> result = searchFunction.search(state, searchUtils);

                putEntry(parent, result.parentEntry);
                putEntry(labels, result.labelEntry);
                if (result.found)
                    return backtrace(start, result.adjacentVertex, parent);
                
                frontier.add(result.adjacentVertex);
            }

        }

        return new ArrayList<>();
    }

    /*
     * This is a helper function that is used to put an entry into a map.
     */
    private <K, B> void putEntry(Map<K, B> map, AbstractMap.SimpleEntry<K, B> entry) {
        map.put(entry.getKey(), entry.getValue());
    }

    /*
     * This is a helper function that is used to backtrace the path from the starting vertex to the goal vertex.
     */
    private List<V> backtrace(V start, V end, Map<V, V> parent) {
        List<V> path = new ArrayList<>();
        while (end != null) {
            path.add(end);  
            end = parent.get(end);
        }
        Collections.reverse(path);
        return path;
    }

    /*
     * A path search function that doesn't use any utility functions such as bfs or dfs.
     */
    public <L> List<V> noUtilsSearchPath(
        V start, 
        V goal, 
        Supplier<Collection<V>> frontierSupplier, 
        Function<V, L> startLabeler
    ) {
        return searchPath(
            start,
            goal,
            LinkedList::new,
            startLabeler,
            (state, utils) -> {
                V w = state.adjE.adj(state.v);
                SearchResult<V, L> result = new SearchResult<>(
                    state.v, 
                    w, 
                    new AbstractMap.SimpleEntry<>(w, state.v), 
                    new AbstractMap.SimpleEntry<>(w, utils.labeler.apply(w)), 
                    false
                );
                if (w.equals(goal))
                    result.found = true;

                return result;
            },
            new SearchUtils<>()
        );
    }

    public <L> List<V> bfs(
        V start, 
        V goal, 
        Function<V, L> startLabeler
    ) {
        return noUtilsSearchPath(start, goal, LinkedList::new, startLabeler);
    }

    public <L> List<V> dfs(
        V start, 
        V goal, 
        Function<V, L> startLabeler
    ) {
        return noUtilsSearchPath(start, goal, Stack::new, startLabeler);
    }

    public static void main(String[] args) {
        AbstractGraph<Integer, Edge<Integer>> graph = new AbstractGraph<Integer, Edge<Integer>>() {};
        graph.addVertices(1, 2, 3, 4);

        graph.addEdges(
            new Edge<>(1, 2){},
            new Edge<>(3, 2){},
            new Edge<>(4, 3){},
            new Edge<>(4, 1){}
        );

        System.out.println(graph.dfs(1, 4, v -> 0));
    }
}

@FunctionalInterface
interface TriConsumer<T, U, V> {
    void accept(T t, U u, V v);
}