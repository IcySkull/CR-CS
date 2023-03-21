package grafos.traversal;

import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

import grafos.edges.AbstractEdge;

public class PostorderSpliterator<V, E extends AbstractEdge<V>> extends TraverseSpliterator<V, E> {

    @Override
    public boolean tryAdvance(Consumer<? super UpcomingVertex<V, E>> action) {
        if (frontier.isEmpty())
            return false;

        Iterator<UpcomingVertex<V, E>> it = frontier.iterator();
        UpcomingVertex<V, E> upcoming = it.next();
        it.remove();

        if (visited.contains(upcoming.v)) {
            closedTrail.accept(upcoming.v);
            return true;
        }

        visited.add(upcoming.v);

        for (E e : graph.adjacentEdges(upcoming.v)) {
            V w = e.adj(upcoming.v);
            frontier.add(new UpcomingVertex<>(upcoming.v, e, w));
        }

        action.accept(upcoming);

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
        return Spliterator.DISTINCT | Spliterator.NONNULL | Spliterator.IMMUTABLE;
    }
}