package grafos;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.*;
import java.util.stream.Collectors;

import grafos.edges.*;
import grafos.function.SearchFunction;
import grafos.function.SearchResult;
import grafos.function.SearchState;
import grafos.function.SearchUtils;
import grafos.function.TraverseFunction;
import grafos.function.TraverseState;

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

            for (E adjE: adjacentEdges(v)) {
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
                V w = state.adjEdge.adj(state.vertex);
                SearchResult<V, L> result = new SearchResult<>(
                    state.vertex, 
                    w, 
                    new AbstractMap.SimpleEntry<>(w, state.vertex), 
                    new AbstractMap.SimpleEntry<>(w, utils.labeler.apply(state.adjEdge)), 
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

    /**
     * instance variable that defines a {@link FunctionalInterface} used to traverse graphs.
     */
    public final TraverseFunction<V, E> traverseFunction = (self, upVertex, fstart, fend) -> {
        if (upVertex.getVisited().contains(upVertex.to))
            return;

        fstart.start(upVertex.to, upVertex.state);

        for (E adjEdge : adjacentEdges(upVertex.to)) {
            TraverseState<V, E>.Upcoming up = upVertex.state.new Upcoming(upVertex.state, upVertex.to, adjEdge);
            self.traverse(self, up, fstart, fend);
        }

        fend.end(upVertex.to, upVertex.state);
    };

    /**
     * A generic function to traverse graphs. It uses the instance variable 
     * {@link #traverseFunction} to traverse the graph.
     * 
     * @param start The starting vertex.
     * 
     * @param frontierSupplier  It provides the collection that determines the order in which the vertices are explored.
     * 
     * @param traversalSupplier A collection supplier to define the order in which the vertices are stored in the 
     *                          traversal collection.
     * 
     * @param visitedSupplier   Visited vertices collection supplier.
     * 
     * @param starting          A function that is called when a vertex is first visited.
     * 
     * @param finalizing        A function that is called when a vertex is completely done being explored.
     * 
     * @return                  A collection of vertices that are traversed. The type of the collection determines the 
     *                          order in which the vertices are stored.
     */
    public Collection<V> traverse(
        V start,
        Supplier<Collection<V>> frontierSupplier,
        Supplier<Collection<V>> traversalSupplier,
        Supplier<Collection<V>> visitedSupplier,
        TraverseFunction.Start<V, E> starting,
        TraverseFunction.End<V, E> finalizing 
    ) {
        Collection<V> frontier = frontierSupplier.get();
        Collection<V> traversal = traversalSupplier.get();
        Collection<V> visited = visitedSupplier.get();

        TraverseState<V, E> state = new TraverseState<>(frontier, traversal, visited);

        starting.start(start, state);

        for (E adjEdge : adjacentEdges(start)) {
            TraverseState<V, E>.Upcoming upVertex = state.new Upcoming(state, start, adjEdge);
            traverseFunction.traverse(traverseFunction, upVertex, starting, finalizing);
        }

        finalizing.end(start, state);

        return traversal;
    }

    public Collection<V> traversePreOrder(
        V start,
        Supplier<Collection<V>> frontierSupplier,
        Supplier<Collection<V>> traversalSupplier,
        Supplier<Collection<V>> visitedSupplier
    ) {
        TraverseFunction.Start<V, E> startFunction = (vertex, state) -> {
            state.addFrontier(vertex);
            state.addTraversal(vertex);
            state.addVisited(vertex);
        };

        TraverseFunction.End<V, E> endFunction = (vertex, state) -> {};

        return traverse(
            start, 
            frontierSupplier, 
            traversalSupplier, 
            visitedSupplier, 
            startFunction, 
            endFunction
        );
    }

    public Collection<V> traversePostOrder(
        V start,
        Supplier<Collection<V>> frontierSupplier,
        Supplier<Collection<V>> traversalSupplier,
        Supplier<Collection<V>> visitedSupplier
    ) {
        TraverseFunction.Start<V, E> startFunction = (vertex, state) -> {
            state.addFrontier(vertex);
            state.addVisited(vertex);
        };

        TraverseFunction.End<V, E> endFunction = (vertex, state) -> {
            state.addTraversal(vertex);
        };

        return traverse(
            start, 
            frontierSupplier, 
            traversalSupplier, 
            visitedSupplier, 
            startFunction, 
            endFunction
        );
    }

    /**
     * Returns a collection of vertices that are traversed following depth-first pre-order.
     * 
     * @param start The starting vertex.
     * @return
     */
    public Collection<V> dft(V start) {
        return traversePreOrder(start, Stack::new, Stack::new, HashSet::new);
    }

    /**
     * Returns a set of connected components of the graph. A connected component is a set 
     * of vertices that are reachable from each other.
     * @return A set of connected components.
     */
    public Set<Collection<V>> connectedComponents() {
        Set<Collection<V>> components = new HashSet<>();
        Set<V> visited = new HashSet<>();

        for (V vertex : vertices()) {
            if (visited.contains(vertex))
                continue;

            Collection<V> component = dft(vertex);
            components.add(component);
            visited.addAll(component);
        }

        return components;
    }
}


