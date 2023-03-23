package grafos.traversal;

import java.util.Collection;
import java.util.Set;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.Supplier;

import grafos.AbstractGraph;
import grafos.edges.AbstractEdge;

public class GraphPostorderSpliterator<V, E extends AbstractEdge<V>> extends TraversalSpliterator<V, E> {

    public GraphPostorderSpliterator() {
        super();
    }

    public GraphPostorderSpliterator(
            AbstractGraph<V, E> graph,
            Supplier<Collection<UpcomingVertex<V, E>>> frontierSupplier,
            Set<V> visited,
            boolean checkVisited
    ) {
        super(graph, frontierSupplier, visited, checkVisited);
    }

    @Override
    public boolean tryAdvance(Consumer<? super UpcomingVertex<V, E>> action) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'tryAdvance'");
    }

    @Override
    public Spliterator<UpcomingVertex<V, E>> trySplit() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'trySplit'");
    }

    @Override
    public long estimateSize() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'estimateSize'");
    }

    @Override
    public int characteristics() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'characteristics'");
    }

}
