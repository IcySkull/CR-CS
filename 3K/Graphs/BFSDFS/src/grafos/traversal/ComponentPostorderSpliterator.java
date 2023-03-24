package grafos.traversal;

import java.util.Collection;
import java.util.Set;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.Supplier;

import grafos.AbstractGraph;
import grafos.edges.AbstractEdge;

public class ComponentPostorderSpliterator<V, E extends AbstractEdge<V>> extends ComponentSpliterator<V, E> {

    public ComponentPostorderSpliterator(
            AbstractGraph<V, E> graph,
            V root,
            Supplier<Collection<UpcomingVertex<V, E>>> frontierSupplier,
            Set<V> visited,
            boolean checkVisited
    ) {
        super(graph, root, frontierSupplier, visited, checkVisited);
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
