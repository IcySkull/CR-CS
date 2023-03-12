package graph;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
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

    public <L, R> R search(
        V start, V goal,
        Supplier<Collection<V>> frontierSupplier, Supplier<Map<V, V>> parentsSupplier, 
        Supplier<Map<V, L>> labelsSupplier, Function<V, L> startLabeler,
        SearchFunction<V, L> searchFunction, SearchFunction.Args<V, E, L> searchArgs,
        TriFunction<V, V, Map<V, V>, R> backtrace, Function<V, R> noPath,
        TriConsumer<AbstractGraph<V, E>, V, V> onVisit, TriConsumer<AbstractGraph<V, E>, V, V> found
    ) {
        Collection<V> frontier = frontierSupplier.get();
        Map<V, V> parent = parentsSupplier.get();
        Map<V, L> labels = labelsSupplier.get();

        frontier.add(start);
        labels.put(start, startLabeler.apply(start));

        while (!frontier.isEmpty()) {
            Iterator<V> it = frontier.iterator();
            V v = it.next();
            it.remove();

            SearchResult<V, L> result = searchFunction.search(
                searchArgs.state(v, frontier, parent, labels, onVisit, found)
            );

            putEntry(parent, result.parentEntry);
            putEntry(labels, result.labelEntry);
            if (result.found)
                return backtrace.apply(start, goal, parent);
            
            frontier.addAll(result.upcomingVertices);
        }

        return noPath.apply(start);
    }

    private <K, B> void putEntry(Map<K, B> map, AbstractMap.SimpleEntry<K, B> entry) {
        map.put(entry.getKey(), entry.getValue());
    }

    public <L, R> R search(
            V start, V goal, 
            Supplier<Collection<V>> frontierSupplier, Supplier<Map<V, V>> parentsSupplier, Supplier<Map<V, L>> labelsSupplier,
            Function<V, L> startLabeler, Function<V, L> labeler, BiFunction<L, L, Boolean> labelerComparator, BinaryOperator<L> labelerCombiner,
            Function<V, L> heuristic, 
            TriFunction<V, V, Map<V,V>, R> backtrace, Function<V, R> noPath,
            TriConsumer<AbstractGraph<V, E>, V, V> onStep, BiConsumer<AbstractGraph<V, E>, V> found
    ) {
        Collection<V> frontier = frontierSupplier.get();
        Map<V, V> parent = parentsSupplier.get();
        Map<V, L> labels = labelsSupplier.get();

        labels.put(start, startLabeler.apply(start));
        frontier.add(start);

        while (!frontier.isEmpty()) {
            Iterator<V> it = frontier.iterator();
            V v = it.next();
            it.remove();

            for (E e : incidentEdges(v)) {
                V w = e.adj(v);
                onStep.accept(this, v, w);
                L label = labelerCombiner.apply(labels.get(v), labeler.apply(w));
                label = labelerCombiner.apply(label, heuristic.apply(w));
                if (w.equals(goal)) {
                    found.accept(this, w);
                    parent.put(w, v);
                    labels.put(w, label);
                    return backtrace.apply(start, goal, parent);
                }
                else if (!labels.containsKey(w) || labelerComparator.apply(label, labels.get(w))) {
                    frontier.add(w);
                    parent.put(w, v);
                    labels.put(w, label);
                }
            }
        }

        return noPath.apply(start);
    }

    private List<V> backtrace(V start, V end, Map<V, V> parent) {
        List<V> path = new ArrayList<>();
        while (end != null) {
            path.add(end);  
            end = parent.get(end);
        }
        Collections.reverse(path);
        return path;
    }

    public <R, L> List<V> bfs(V start, V end, TriConsumer<AbstractGraph<V, E>, V, V> onStep, BiConsumer<AbstractGraph<V, E>, V> found) {
        return search(start, end, 
            LinkedList::new, HashMap::new, HashMap::new,
            startLabeler -> 0, labeler -> 0, (l1, l2) -> false, (l1, l2) -> l1 + l2, 
            v -> 0,
            (s, e, parentMap) -> backtrace(start, end, parentMap), noPath -> null,
            onStep, found
        );
    }

    public static void main(String[] args) {
        AbstractGraph<Integer, Edge<Integer>> g = new AbstractGraph<Integer, Edge<Integer>>() {};
        g.addVertices(1, 2, 3, 4);
        g.addEdge(new Edge<Integer>(1, 2) {});
        g.addEdge(new Edge<Integer>(3, 2) {});
        g.addEdge(new Edge<Integer>(4, 3) {});
        g.addEdge(new Edge<Integer>(1, 4) {});

        System.out.println(g.bfs(1, 4, (graph, from, to) -> {
            System.out.println("Step: " + from + " -> " + to);
        }, (graph, v) -> {
            System.out.println("Found: " + v);
        }));
    }
}

@FunctionalInterface
interface TriFunction<T, U, V, R> {
    R apply(T t, U u, V v);
}

@FunctionalInterface
interface TriConsumer<T, U, V> {
    void accept(T t, U u, V v);
}

class NullMap<K, V> extends HashMap<K, V> {
    @Override
    public V get(Object key) {
        return null;
    }

    @Override
    public V put(K key, V value) {
        return null;
    }
}