package grafos.traversal;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.Supplier;

import grafos.AbstractGraph;
import grafos.edges.AbstractEdge;

public abstract class GraphTraversalSpliterator<V, E extends AbstractEdge<V>> implements Spliterator<VertexTraversalSpliterator<V, E>> {
    private final AbstractGraph<V, E> graph;
    private VertexTraversalSpliterator<V, E> current;
    private final Supplier<Collection<UpcomingVertex<V,E>>> frontierSupplier;
    private final Set<V> visited;
    private final Set<V> toExplore;
    private final boolean checkVisited;

    public GraphTraversalSpliterator() {
        graph = null;
        frontierSupplier = null;
        visited = null;
        toExplore = null;
        checkVisited = false;
    }

    public GraphTraversalSpliterator(
            AbstractGraph<V, E> graph,
            Supplier<Collection<UpcomingVertex<V,E>>> frontierSupplier,
            Set<V> visited,
            boolean checkVisited
    ) {
        this.graph = graph;
        this.frontierSupplier = frontierSupplier;
        this.visited = visited;
        this.toExplore = new HashSet<>();
        this.checkVisited = checkVisited;
    }   

}
