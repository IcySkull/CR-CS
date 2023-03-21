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
import grafos.function.TraverseState.Upcoming;

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

    public class UpcomingVertex {
        public final V sourceVertex;
        public final E sourceEdge;
        public final V upVertex;

        public UpcomingVertex(V sourceVertex, E sourceEdge, V upVertex) {
            this.sourceVertex = sourceVertex;
            this.sourceEdge = sourceEdge;
            this.upVertex = upVertex;
        }
    }

    /**
     * Base function for traversing the graph. It provides the logic behind
     * performing any
     * traversal of a graph. It is used thorughout all the traversal functions.
     * 
     * @param start      The starting vertex.
     * 
     * @param traversal  The collection that will store the traversal.
     * 
     * @param visited    The set of visited vertices.
     * 
     * @param starting   The function that will be called when a vertex is first
     *                   visited.
     * 
     * @param finalizing The function that will be called when a vertex is finished
     *                   being visited.
     * 
     * @param cycle      The function that will be called when closed trail is
     *                   found.
     * @return The traversal of reachable vertices from the starting vertex.
     */
    protected Collection<V> traverse(
            V start,
            Collection<UpcomingVertex> frontier,
            Collection<V> traversal,
            Set<V> visited,
            TraverseFunction.Start<V, E> starting,
            TraverseFunction.End<V, E> finalizing,
            Consumer<V> cycle) {
        frontier.add(new UpcomingVertex(null, null, start));

        while (!frontier.isEmpty()) {
            Iterator<UpcomingVertex> it = frontier.iterator();
            UpcomingVertex v = it.next();
            it.remove();

            if (visited.contains(v.upVertex)) {
                cycle.accept(v.upVertex);
                continue;
            }

            visited.add(v.upVertex);

            starting.start(v);

            for (E adjE : adjacentEdges(v.upVertex)) {
                V adjV = adjE.adj(v.upVertex);
                frontier.add(new UpcomingVertex(v.upVertex, adjE, adjV));
            }

            finalizing.end(v);
        }

        return traversal;
    }

    /**
     * Traverses the entirity of the graph, essentially storing each component of
     * the graph in the set.
     * 
     * @param traversal  The collection that will store the traversal.
     * 
     * @param visited    The set of visited vertices.
     * 
     * @param starting   The function that will be called when a vertex is first
     *                   visited.
     * 
     * @param finalizing The function that will be called when a vertex is finished
     *                   being visited.
     * 
     * @param cycle      The function that will be called when closed trail is
     *                   found.
     * 
     * @return The set of traversals of each component of the graph.
     */
    protected Set<Collection<V>> traverse(
            Supplier<Collection<UpcomingVertex>> frontierSupplier,
            Supplier<Collection<V>> traversalSupplier,
            Set<V> visited,
            TraverseFunction.Start<V, E> starting,
            TraverseFunction.End<V, E> finalizing,
            Consumer<V> cycle) {
        return vertices().stream()
                .reduce(
                        new HashSet<>(),
                        (traversals, v) -> {
                            if (visited.contains(v))
                                return traversals;

                            traversals.add(traverse(
                                    v,
                                    frontierSupplier.get(),
                                    traversalSupplier.get(),
                                    visited,
                                    starting,
                                    finalizing,
                                    cycle));

                            return traversals;
                        },
                        (set1, set2) -> {
                            set1.addAll(set2);
                            return set1;
                        });
    }

    /**
     * Traverses all the rechable vertices from the given vertex of the graph in a
     * pre-order fashion.
     */
    public Collection<V> traversePreOrder(
            V start,
            Supplier<Collection<UpcomingVertex>> frontierSupplier,
            Supplier<Collection<V>> traversalSupplier,
            Set<V> visited,
            Consumer<V> onVisit,
            Consumer<V> onExit,
            Consumer<V> cycle) {
        Collection<V> traversal = traversalSupplier.get();

        TraverseFunction.Start<V, E> startFunction = v -> {
            traversal.add(v.upVertex);
            onVisit.accept(v.upVertex);
        };

        TraverseFunction.End<V, E> endFunction = v -> {
            onExit.accept(v.upVertex);
        };

        return traverse(
                start,
                frontierSupplier.get(),
                traversal,
                visited,
                startFunction,
                endFunction,
                cycle);
    }

    /*
     * Traverses the entire graph in a pre-order fashion.
     */
    public Set<Collection<V>> traversePreOrder(
            Supplier<Collection<UpcomingVertex>> frontierSupplier,
            Supplier<Collection<V>> traversalSupplier,
            Set<V> visited,
            Consumer<V> onVisit,
            Consumer<V> onExit,
            Consumer<V> cycle) {
        return vertices().stream()
                .reduce(
                        new HashSet<>(),
                        (traversals, vertex) -> {
                            if (visited.contains(vertex))
                                return traversals;

                            traversals.add(traversePreOrder(
                                    vertex,
                                    frontierSupplier,
                                    traversalSupplier,
                                    visited,
                                    onVisit,
                                    onExit,
                                    cycle));

                            return traversals;
                        },
                        (traversals1, traversals2) -> {
                            traversals1.addAll(traversals2);
                            return traversals1;
                        });
    }

    /*
     * Traverses all the reachable vertices from the given vertex of the graph in a
     * post-order fashion.
     */
    public Collection<V> traversePostOrder(
            V start,
            Supplier<Collection<UpcomingVertex>> frontierSupplier,
            Supplier<Collection<V>> traversalSupplier,
            Set<V> visited,
            Consumer<V> onVisit,
            Consumer<V> onExit,
            Consumer<V> cycle) {
        Collection<V> traversal = traversalSupplier.get();

        TraverseFunction.Start<V, E> startFunction = v -> {
            onVisit.accept(v.upVertex);
        };

        TraverseFunction.End<V, E> endFunction = v -> {
            traversal.add(v.upVertex);
            onExit.accept(v.upVertex);
        };

        return traverse(
                start,
                frontierSupplier.get(),
                traversal,
                visited,
                startFunction,
                endFunction,
                cycle);
    }

    /*
     * Traverses the entire graph in a post-order fashion.
     */
    public Set<Collection<V>> traversePostOrder(
            Supplier<Collection<UpcomingVertex>> frontierSupplier,
            Supplier<Collection<V>> traversalSupplier,
            Set<V> visited,
            Consumer<V> onVisit,
            Consumer<V> onExit,
            Consumer<V> cycle) {
        return vertices().stream()
                .reduce(
                        new HashSet<>(),
                        (traversals, vertex) -> {
                            if (visited.contains(vertex))
                                return traversals;

                            traversals.add(traversePostOrder(
                                    vertex,
                                    frontierSupplier,
                                    traversalSupplier,
                                    visited,
                                    onVisit,
                                    onExit,
                                    cycle));

                            return traversals;
                        },
                        (traversals1, traversals2) -> {
                            traversals1.addAll(traversals2);
                            return traversals1;
                        });
    }

    public Set<Collection<V>> dfsPreOrder(
            Set<V> visited,
            Consumer<V> onVisit,
            Consumer<V> onExit,
            Consumer<V> cycle) {
        return traversePreOrder(
                Stack::new,
                LinkedList::new,
                visited,
                onVisit,
                onExit,
                cycle);
    }

    public Set<Collection<V>> dfsPostOrder(
            Set<V> visited,
            Consumer<V> starting,
            Consumer<V> ending,
            Consumer<V> cycle) {
        return traversePostOrder(
                Stack::new,
                LinkedList::new,
                visited,
                starting,
                ending,
                cycle);
    }

    public Set<Collection<V>> dfsReversePostOrder(
            Set<V> visited,
            Consumer<V> starting,
            Consumer<V> ending,
            Consumer<V> cycle) {
        return traversePostOrder(
                Stack::new,
                Stack::new,
                visited,
                starting,
                ending,
                cycle);
    }

    /**
     * Traverses the graph using Depth First Search in a pre-order as the default.
     */
    public Collection<V> traverse() {
        return dfsPreOrder(
                new HashSet<>(),
                v -> {
                },
                v -> {
                },
                v -> {
                }).stream()
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    /*
     * Traverses from the given vertex using Depth First Search in a pre-order as
     * the default.
     */
    public Collection<V> traverse(V start) {
        return traversePreOrder(
                start,
                Stack::new,
                LinkedList::new,
                new HashSet<>(),
                v -> {
                },
                v -> {
                },
                v -> {
                });
    }

    /**
     * Traverses the entire graph using Depth First Search in a pre-order as the
     * default.
     * 
     * @return the set of connected components in the graph.
     */
    public Set<Collection<V>> connectedComponents() {
        return dfsPreOrder(
                new HashSet<>(),
                v -> {
                },
                v -> {
                },
                v -> {
                });
    }

    /**
     * Returns a set of cycles in the graph, not necessarily all cycles, but cycles
     * from performing one
     * simple traversal using Depth First Search. But it is guaranteed that some
     * cycles will be found
     * if there are any cycles.
     * 
     */
    public Set<Cycle<V>> cycles() {
        Set<Cycle<V>> cycles = new HashSet<>();
        Set<V> visited = new HashSet<>();
        Stack<V> stacked = new Stack<>();

        dfsPreOrder(
                visited,
                v -> stacked.push(v),
                v -> stacked.pop(),
                v -> {
                    System.out.println(stacked);
                    int index = stacked.indexOf(v);
                    if (stacked.size() - index < 3 || index == -1)
                        return;
                    cycles.add(new Cycle<>(stacked.subList(index, stacked.size())));
                });

        return cycles;
    }
}
