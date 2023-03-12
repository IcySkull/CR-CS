package graph;

import java.util.Collection;
import java.util.Map;

@FunctionalInterface
public interface SearchFunction<V, L> {
    public <T> SearchResult<V, L> search(T... args);

    @FunctionalInterface
    public interface Args<V, E extends Edge<V>, L> {
        public <T> Object[] state(V nextVertex, Collection<V> frontier, Map<V, V> parent, Map<V, L> labels, TriConsumer<AbstractGraph<V, E>, V, V> onVisit, TriConsumer<AbstractGraph<V, E>, V, V> found);
    }
}
