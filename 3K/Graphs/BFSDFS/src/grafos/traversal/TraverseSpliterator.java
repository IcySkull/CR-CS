package grafos.traversal;

import java.util.Collection;
import java.util.Set;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.Supplier;

import grafos.AbstractGraph;
import grafos.edges.AbstractEdge;

public abstract class TraverseSpliterator<V, E extends AbstractEdge<V>> implements Spliterator<UpcomingVertex<V, E>>{
    protected final AbstractGraph<V, E> graph;
    protected Collection<UpcomingVertex<V, E>> frontier;
    protected Set<V> visited;
    protected Consumer<V> closedTrail;

    public TraverseSpliterator() {
        this.graph = null;
        this.frontier = null;
        this.visited = null;
        this.closedTrail = v -> {};
    }

    public TraverseSpliterator(
        AbstractGraph<V, E> graph, 
        V root,
        Supplier<Collection<UpcomingVertex<V, E>>> frontierSupplier,
        Set<V> visited
    ) {
        this.graph = graph;
        this.frontier = frontierSupplier.get();
        this.visited = visited;
        this.closedTrail = v -> {};

        this.frontier.add(new UpcomingVertex<>(null, null, root));
    }

    public TraverseSpliterator(
        AbstractGraph<V, E> graph, 
        V root,
        Supplier<Collection<UpcomingVertex<V, E>>> frontierSupplier,
        Set<V> visited,
        Consumer<V> closedTrail
    ) {
        this(graph, root, frontierSupplier, visited);
        this.closedTrail = closedTrail;
    }
}
