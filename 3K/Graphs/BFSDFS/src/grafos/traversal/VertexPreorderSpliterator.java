package grafos.traversal;

import java.util.Collection;
import java.util.Set;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.Supplier;

import grafos.AbstractGraph;
import grafos.edges.AbstractEdge;

public class VertexPreorderSpliterator<V, E extends AbstractEdge<V>> extends VertexTraversalSpliterator<V, E> {

    public VertexPreorderSpliterator() {
        super();
    }

    public VertexPreorderSpliterator(
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
        if (frontier.isEmpty())
            return false;

        UpcomingVertex<V, E> upcoming = frontier.consume();

        if (!checkVisited)
            action.accept(upcoming);

        if (visited.contains(upcoming.v)) {
            return true;
        }

        if (checkVisited)
            action.accept(upcoming);

        visited.add(upcoming.v);

        for (E e : graph.adjacentEdges(upcoming.v)) {
            V w = e.adj(upcoming.v);
            frontier.add(new UpcomingVertex<>(upcoming.v, e, w));
        }

        return true;
    }

    @Override
    public Spliterator<UpcomingVertex<V, E>> trySplit() {
        return null;
    }

    @Override
    public long estimateSize() {
        return frontier.size() + visited.size();
    }

    @Override
    public int characteristics() {
        return Spliterator.DISTINCT | Spliterator.NONNULL | Spliterator.IMMUTABLE;
    }
    
}
