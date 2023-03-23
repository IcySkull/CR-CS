package grafos.traversal;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.Supplier;

import grafos.AbstractGraph;
import grafos.edges.AbstractEdge;

public class GraphPreorderSpliterator<V, E extends AbstractEdge<V>> extends TraversalSpliterator<V, E> {
    private Set<V> toExplore;

    public GraphPreorderSpliterator() {
        super();
    }

    public GraphPreorderSpliterator(
            AbstractGraph<V, E> graph,
            Supplier<Collection<UpcomingVertex<V, E>>> frontierSupplier,
            Set<V> visited,
            boolean checkVisited
            ) {
        super(graph, frontierSupplier, visited, checkVisited);

        toExplore = new HashSet<>(graph.vertices());
    }

    @Override
    public boolean tryAdvance(Consumer<? super UpcomingVertex<V, E>> action) {
        if (frontier.isEmpty() && toExplore.isEmpty())
            return false;
        
        if (frontier.isEmpty()) {
            V v = toExplore.iterator().next();
            frontier.add(new UpcomingVertex<>(null, null, v));
            toExplore.remove(v);
        }

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
            toExplore.remove(w);
        }

        return true;
    }

    @Override
    public Spliterator<UpcomingVertex<V, E>> trySplit() {
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
