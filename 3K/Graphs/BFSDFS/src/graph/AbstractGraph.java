package graph;

import java.util.*;
import java.util.function.*;
import java.util.stream.Collectors;

import graph.SearchFunction.*;

/**
 * A graph is a set of vertices and a set of edges. Each edge connects two
 * vertices. Note that AbstractGraph ignores if directed or undirected.
 * 
 */
public abstract class AbstractGraph<V, E extends AbstractEdge<V>> {


    public abstract Set<V> getVertices();

    public abstract Set<E> getEdges();

    public abstract void addVertex(V v);

    public abstract void addEdge(E e);

    /**
     * Returns the number of vertices in the graph.
     */
    public int order() {
        return getVertices().size();
    }

    /**
     * Returns the number of edges in the graph.
     */
    public int numEdges() {
        return getEdges().size();
    }

    /**
     * Returns the set of edges incident to vertex v.
     */
    public Set<E> incidentEdges(V u) {
        return getEdges().stream()
            .filter(e -> e.isIncident(u))
            .collect(Collectors.toSet());
    }

    /**
     * Returns the set of edges adjacent to edge e.
     */
    public Set<E> adjacentEdges(E e) {
        return getEdges().stream()
            .filter(e2 -> e2.equals(e) && e2 != e)
            .collect(Collectors.toSet());
    }

    /**
     * Returns the set of vertices adjacent to vertex v.
     */
    public Set<V> adjacentVertices(V u) {
        return getEdges().stream()
        .filter(e -> e.isIncident(u))
        .map(e -> e.adj(u))
        .collect(Collectors.toSet());
    }

    /*
     * Edges that allow flow out of vertex v. Used for search path algorithms.
     */
    public abstract Set<E> outEdges(V v);

    /**
     * Returns the degree of verte cx v.
     */
    public int degree(V u) {
        return incidentEdges(u).size();
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

            for (E adjE: outEdges(v)) {
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

    public <L> List<V> searchPathRecursive(
        V start, 
        V goal,
        Supplier<Collection<V>> frontierSupplier, 
        Function<V, L> startLabeler,
        SearchFunction<V, E, L> searchFunction, 
        SearchUtils<V, E, L> searchUtils
    ) {
        Collection<V> frontier = frontierSupplier.get();
        Map<V, L> labels = new HashMap<>();

        frontier.add(start);
        labels.put(start, startLabeler.apply(start));

        return searchPathRecursive(start, goal, frontier, labels, searchFunction, searchUtils);
    }

    private <L> List<V> searchPathRecursive(
        V start, 
        V goal,
        Collection<V> frontier,
        Map<V, L> labels,
        SearchFunction<V, E, L> searchFunction, 
        SearchUtils<V, E, L> searchUtils
    ) {
        if (frontier.isEmpty())
            return null;

        Iterator<V> it = frontier.iterator();
        V v = it.next();
        it.remove();

        for (E adjE: outEdges(v)) {
            if (labels.containsKey(adjE.adj(v)))
                continue;
            SearchState<V, E, L> state = new SearchState<>(v, adjE, frontier, null, labels);

            SearchResult<V, L> result = searchFunction.search(state, searchUtils);

            putEntry(labels, result.labelEntry);
            if (result.found)
                return backtrace(start, result.adjacentVertex, null);
            
            frontier.add(result.adjacentVertex);
        }

        return searchPathRecursive(start, goal, frontier, labels, searchFunction, searchUtils);
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
}

/*
 * An edge is a pair of vertices. It is directed in the sense that vertices v
 * and w are ordered, giving a diferent hashCode for (v, w) than for (w, v).
 * Nevertheless, adj() method is undirected. Directed or undirected should be 
 * determined by its implementations, taking into account adj() method.
 */
abstract class AbstractEdge<V> {
    private V v;
    private V w;

    protected AbstractEdge(V v, V w) {
        this.v = v;
        this.w = w;
    }

    public V v() {
        return v;
    }

    public V w() {
        return w;
    }

    /*
     * Return the adjacent vertex to u in the edge.
     */
    public abstract V adj(V u);

    /*
     * True if u is one of the vertices of this edge, false otherwise.
     */
    public boolean isIncident(V u) {
        return u.equals(v) || u.equals(w);
    }

    /*
     * Hashcode must always be implemented since it dictates the Set behavior.
     */
    public abstract int hashCode();

    public abstract boolean equals(Object obj);
}

@FunctionalInterface
interface TriConsumer<T, U, V> {
    void accept(T t, U u, V v);
}

/*
 * Functional interface for search algorithms. It is a function that takes 
 * {@code SearchState} and {@code SearchUtils} as parameters and returns a {@code SearchResult}.
 * It is used to implement search algorithms such as BFS, DFS, Dijkstra, etc. with the 
 * archetypal {@code searchPath} function in {@code AbstractGraph}. 
 */
@FunctionalInterface
interface SearchFunction<V, E extends AbstractEdge<V>, L> {
    public SearchResult<V, L> search(SearchState<V, E, L> state, SearchUtils<V, E,L> utils);

    public class SearchResult<V, L> {
        public final V vertex;
        public final V adjacentVertex;
        public final AbstractMap.SimpleEntry<V, V> parentEntry;
        public final AbstractMap.SimpleEntry<V, L> labelEntry;
        public Boolean found;
        
        public SearchResult(V vertex, V adjacentVertex, AbstractMap.SimpleEntry<V, V> parentEntry, AbstractMap.SimpleEntry<V, L> labelEntry, Boolean found) {
            this.vertex = vertex;
            this.adjacentVertex = adjacentVertex;
            this.parentEntry = parentEntry;
            this.labelEntry = labelEntry;
            this.found = found;
        }
    }

    public class SearchState<V, E extends AbstractEdge<V>, L> {
        public final V v;
        public final E adjE;
        public final Collection<V> frontier;
        public final Map<V, V> parent;
        public final Map<V, L> labels;

        public SearchState(V v, E adjE, Collection<V> frontier, Map<V, V> parent, Map<V, L> labels) {
            this.v = v;
            this.adjE = adjE;
            this.frontier = frontier;
            this.parent = parent;
            this.labels = labels;
        }
    }

    public class SearchUtils<V, E extends AbstractEdge<V>, L> {
        public final Function<V, L> labeler;
        public final BiFunction<L, L, Boolean> labelerCmp;
        public final BinaryOperator<L> labelerAcc;
        public final Function<V, L> heuristic;
        public final TriConsumer<AbstractGraph<V, E>, V, V> onVisit;

        public SearchUtils() {
            this.labeler = v1 -> null;
            this.labelerCmp = (l1, l2) -> false;
            this.labelerAcc = (l1, l2) -> null;
            this.heuristic = v -> null;
            this.onVisit = (g, v1, v2) -> {};
        }

        public SearchUtils(
            Function<V, L> labeler, 
            BiFunction<L, L, Boolean> labelerCmp, 
            BinaryOperator<L> labelerAcc, 
            Function<V, L> heuristic,
            TriConsumer<AbstractGraph<V, E>, V, V> onVisit
        ) {
            this.labeler = labeler;
            this.labelerCmp = labelerCmp;
            this.labelerAcc = labelerAcc;
            this.heuristic = heuristic;
            this.onVisit = onVisit;
        }
    }
}