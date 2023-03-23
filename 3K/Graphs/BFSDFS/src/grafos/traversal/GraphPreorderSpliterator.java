package grafos.traversal;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.Supplier;

import grafos.AbstractGraph;
import grafos.edges.AbstractEdge;

public class GraphPreorderSpliterator<V, E extends AbstractEdge<V>> extends GraphTraversalSpliterator<V, E> {

    public GraphPreorderSpliterator(
            AbstractGraph<V, E> graph,
            Supplier<Collection<UpcomingVertex<V, E>>> frontierSupplier,
            Set<V> visited,
            boolean checkVisited
    ) {
        super(graph, frontierSupplier, visited, checkVisited);
    }

    @Override
    public boolean tryAdvance(Consumer<? super VertexTraversalSpliterator<V,E>> action) {
        
    }

    @Override
    public Spliterator<VertexTraversalSpliterator<V,E>> trySplit() {
        return null;
    }

    @Override
    public long estimateSize() {
        return Long.MAX_VALUE;
    }

    @Override
    public int characteristics() {
        return Spliterator.DISTINCT | Spliterator.NONNULL | Spliterator.ORDERED;
    }
}
