package grafos;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import grafos.edges.*;
import grafos.function.*;
import grafos.traversal.*;
import grafos.traversal.VertexTraversalSpliterator.*;

/**
 * A graph is a set of vertices and a set of edges. Each edge connects two
 * vertices. Note that AbstractGraph ignores if directed or undirected.
 * 
 */
public abstract class AbstractGraph<V, E extends AbstractEdge<V>> {

    public abstract Collection<V> vertices();

    public abstract Collection<E> edges();

    public abstract void addVertex(V v);

    public abstract void addEdge(E e);

    /**
     * Returns the number of vertices in the graph.
     */
    public int order() {
        return vertices().size();
    }

    /**
     * Returns the number of edges in the graph.
     */
    public int numEdges() {
        return edges().size();
    }

    /*
     * Returns the set of edges adjacent to vertex v.
     */
    public abstract Collection<E> adjacentEdges(V u);

    /**
     * Returns the degree of verte cx v.
     */
    public int degree(V u) {
        return adjacentEdges(u).size();
    }

    /**
     * Archetypal search function. It is a generic function that can be used to
     * implement any
     * search algorithm.
     * 
     * @param <L>
     * @param start            The starting vertex
     * @param goal             The goal vertex
     * @param frontierSupplier A supplier of a collection that will be used to store
     *                         upcoming vertices to explore,
     *                         e.g. a queue or a stack. The collection type is
     *                         specially important since it will
     *                         determine the order in which the vertices are
     *                         explored.
     * @param startLabeler     A function that will be used to label the starting
     *                         vertex, it aslo determines the type
     *                         of the {@code L} generic parameter.
     * @param searchFunction   A functional interface how the upcoming vertices are
     *                         explored.
     * @param searchUtils      A utility class that contains useful functions for
     *                         the search.
     * @return A list of vertices that form a path from the starting vertex to the
     *         goal vertex.
     */
    public <L> List<V> searchPath(
            V start,
            V goal,
            Supplier<Collection<V>> frontierSupplier,
            Function<V, L> startLabeler,
            SearchFunction<V, E, L> searchFunction,
            SearchUtils<V, E, L> searchUtils) {
        Collection<V> frontier = frontierSupplier.get();
        Map<V, V> parent = new HashMap<>();
        Map<V, L> labels = new HashMap<>();

        frontier.add(start);
        labels.put(start, startLabeler.apply(start));

        while (!frontier.isEmpty()) {
            Iterator<V> it = frontier.iterator();
            V v = it.next();
            it.remove();

            for (E adjE : adjacentEdges(v)) {
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

        return null;
    }

    /*
     * This is a helper function that is used to put an entry into a map.
     */
    private <K, B> void putEntry(Map<K, B> map, AbstractMap.SimpleEntry<K, B> entry) {
        map.put(entry.getKey(), entry.getValue());
    }

    /*
     * This is a helper function that is used to backtrace the path from the
     * starting vertex to the goal vertex.
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
     * A path search function that doesn't use any utility functions such as bfs or
     * dfs.
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
                    V w = state.adjEdge.adj(state.vertex);
                    SearchResult<V, L> result = new SearchResult<>(
                            state.vertex,
                            w,
                            new AbstractMap.SimpleEntry<>(w, state.vertex),
                            new AbstractMap.SimpleEntry<>(w, utils.labeler.apply(state.adjEdge)),
                            false);
                    if (w.equals(goal))
                        result.found = true;

                    return result;
                },
                new SearchUtils<>());
    }

    public Spliterator<UpcomingVertex<V, E>> spliterator(
        Scope scope,
        V root,
        Frontier frontier, 
        Order order,
        Set<V> visited,
        boolean checkVisited
    ) {
        return VertexTraversalSpliterator.of(this, scope, root, frontier, order, visited, checkVisited);
    }

    public Stream<UpcomingVertex<V, E>> stream(
        Scope scope,
        V root,
        Frontier frontier, 
        Order order,
        Set<V> visited,
        boolean checkVisited
    ) {
        return StreamSupport.stream(spliterator(scope, root, frontier, order, visited, checkVisited), false);
    }

    public List<V> dfs() {
        return stream(
            Scope.GRAPH, 
            null, 
            Frontier.DFS, 
            Order.PREORDER, 
            new HashSet<>(), 
            true)
            .map(up -> up.v)
            .collect(Collectors.toList());
    }

    public List<V> dfs(V root) {
        return stream(
            Scope.VERTEX, 
            root, 
            Frontier.DFS, 
            Order.PREORDER, 
            new HashSet<>(), 
            true)
            .map(up -> up.v)
            .collect(Collectors.toList());
    }

    public List<V> bfs() {
        return stream(
            Scope.GRAPH, 
            null, 
            Frontier.BFS, 
            Order.PREORDER, 
            new HashSet<>(), 
            true)
            .map(up -> up.v)
            .collect(Collectors.toList());
    }

    public List<V> bfs(V root) {
        return stream(
            Scope.VERTEX, 
            root, 
            Frontier.BFS, 
            Order.PREORDER, 
            new HashSet<>(), 
            true)
            .map(up -> up.v)
            .collect(Collectors.toList());
    }

    public boolean reachable(V start, V end) {
        return stream(
            Scope.VERTEX, 
            start, 
            Frontier.DFS, 
            Order.PREORDER, 
            new HashSet<>(), 
            true)
            .anyMatch(up -> up.v.equals(end));
    }
}
