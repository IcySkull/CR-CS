package grafos;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
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
            Function<V, L> startLabeler) {
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

    public <L> List<V> bfs(
            V start,
            V goal,
            Function<V, L> startLabeler) {
        return noUtilsSearchPath(start, goal, LinkedList::new, startLabeler);
    }

    public <L> List<V> dfs(
            V start,
            V goal,
            Function<V, L> startLabeler) {
        return noUtilsSearchPath(start, goal, Stack::new, startLabeler);
    }

    public Collection<V> traverse(
            V start,
            Collection<V> traversal,
            Set<V> visited,
            TraverseFunction.Start<V, E> starting,
            TraverseFunction.End<V, E> finalizing,
            Consumer<V> cycle) {
        starting.start(start);

        TraverseFunction<V, E> traverseFunction = (self, from, adjEdge, fstart, fend, fcycle) -> {
            V to = adjEdge.adj(from);
            if (visited.contains(to)) {
                fcycle.accept(to);
                return;
            }

            fstart.start(to);

            for (E e : adjacentEdges(to)) {
                self.traverse(self, to, e, fstart, fend, fcycle);
            }

            fend.end(to);
        };

        starting.start(start);

        for (E adjEdge : adjacentEdges(start)) {
            traverseFunction.traverse(traverseFunction, start, adjEdge, starting, finalizing, cycle);
        }

        finalizing.end(start);
        return traversal;
    }

    public Collection<V> traverse(
            Collection<V> traversal,
            Set<V> visited,
            TraverseFunction.Start<V, E> starting,
            TraverseFunction.End<V, E> finalizing,
            Consumer<V> cycle) {
        for (V v : vertices()) {
            if (visited.contains(v))
                continue;

            traversal.addAll(
                    traverse(
                            v,
                            traversal,
                            visited,
                            starting,
                            finalizing,
                            cycle));
        }
        return traversal;
    }

    public Set<Set<V>> traverse(
            Set<Set<V>> components,
            Supplier<Set<V>> traversalSupplier,
            Set<V> visited,
            TraverseFunction.Start<V, E> starting,
            TraverseFunction.End<V, E> finalizing,
            Consumer<V> cycle) {
        for (V v : vertices()) {
            if (visited.contains(v))
                continue;

            Set<V> traversal = (Set<V>) traverse(
                    v,
                    traversalSupplier.get(),
                    visited,
                    starting,
                    finalizing,
                    cycle);
            components.add(traversal);
        }
        return components;
    }

    public Collection<V> traversePreOrder(
            Supplier<Collection<V>> frontierSupplier,
            Supplier<Collection<V>> traversalSupplier,
            Set<V> visited,
            Consumer<V> starting,
            Consumer<V> finalizing,
            Consumer<V> cycle
    ) {
        Collection<V> frontier = frontierSupplier.get();
        Collection<V> traversal = traversalSupplier.get();
        
        TraverseFunction.Start<V, E> startFunction = (vertex) -> {
            frontier.add(vertex);
            traversal.add(vertex);
            visited.add(vertex);
            starting.accept(vertex);
        };

        TraverseFunction.End<V, E> endFunction = (vertex) -> {
            finalizing.accept(vertex);
        };

        return traverse(
                traversal,
                visited,
                startFunction,
                endFunction,
                cycle);
    }

    public Collection<V> traversePostOrder(
            Supplier<Collection<V>> frontierSupplier,
            Supplier<Collection<V>> traversalSupplier,
            Set<V> visited,
            Consumer<V> starting,
            Consumer<V> ending,
            Consumer<V> cycle
    ) {
        Collection<V> frontier = frontierSupplier.get();
        Collection<V> traversal = traversalSupplier.get();

        TraverseFunction.Start<V, E> startFunction = (vertex) -> {
            frontier.add(vertex);
            visited.add(vertex);
            starting.accept(vertex);
        };

        TraverseFunction.End<V, E> endFunction = (vertex) -> {
            traversal.add(vertex);
            ending.accept(vertex);
        };

        return traverse(
                traversal,
                visited,
                startFunction,
                endFunction,
                cycle);
    }

    public Collection<V> dfsPreOrder(
            Set<V> visited,
            Consumer<V> starting,
            Consumer<V> ending,
            Consumer<V> cycle) {
        Supplier<Collection<V>> frontierSupplier = () -> new Stack<>();
        Supplier<Collection<V>> traversalSupplier = () -> (Queue)new LinkedList<>();

        return traversePreOrder(
                frontierSupplier,
                traversalSupplier,
                visited,
                starting,
                ending,
                cycle);
    }

    public Collection<V> dfsPostOrder(
            Set<V> visited,
            Consumer<V> starting,
            Consumer<V> ending,
            Consumer<V> cycle) {
        Supplier<Collection<V>> frontierSupplier = () -> new Stack<>();
        Supplier<Collection<V>> traversalSupplier = () -> (Queue) new LinkedList<>();

        return traversePostOrder(
                frontierSupplier,
                traversalSupplier,
                visited,
                starting,
                ending,
                cycle);
    }

    public Collection<V> dfsReversePostOrder(
            Set<V> visited,
            Consumer<V> starting,
            Consumer<V> ending,
            Consumer<V> cycle) {
        Supplier<Collection<V>> frontierSupplier = () -> new Stack<>();
        Supplier<Collection<V>> traversalSupplier = () -> new Stack<>();

        return traversePostOrder(
                frontierSupplier,
                traversalSupplier,
                visited,
                starting,
                ending,
                cycle);
    }

    public Set<V> traverse() {
        return (Set<V>) traverse(new HashSet<>(), new HashSet<>(), (v) -> {
        }, (v) -> {
        }, (v) -> {
        });
    }

    /**
     * Returns a set of connected components of the graph. A connected component is
     * a set
     * of vertices that are reachable from each other.
     * 
     * @return A set of connected components.
     */
    public Set<Set<V>> connectedComponents() {
        Set<Set<V>> components = new HashSet<>();
        Supplier<Set<V>> traversalSupplier = () -> new HashSet<>();

        return (Set<Set<V>>) traverse(
                components,
                traversalSupplier,
                new HashSet<>(),
                (v) -> {
                },
                (v) -> {
                },
                (v) -> {
                });
    }

    /**
     * Returns a set of cycles in the graph. O((|V| + |E|)^2)
     * 
     * @return
     */
    public Set<Cycle<V>> cycles() {
        Set<Cycle<V>> cycles = new HashSet<>();
        Set<V> visited = new HashSet<>();
        Stack<V> stacked = new Stack<>();

        dfsPreOrder(
            visited,
            (v) -> stacked.push(v),
            (v) -> stacked.pop(),
            (v) -> {
                if (stacked.peek() == v) {
                    List<V> path = new ArrayList<>();
                    while (stacked.peek() != v)
                        path.add(stacked.pop());

                    Cycle<V> cycle = new Cycle<>(path);
                    cycles.add(cycle);
                }
            }
        );

        return cycles;
    }
}
