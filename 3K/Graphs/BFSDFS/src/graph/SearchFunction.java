package graph;

import java.util.Collection;
import java.util.Map;

@FunctionalInterface
public interface SearchFunction<V, L> {
    public <T> SearchResult<V, L> search(T... args);

    @FunctionalInterface
    public interface Args<V, E extends Edge<V>, L> {
        public <T> Object[] args(V fromm, V to, Collection<V> frontier, Map<V, V> parent, Map<V, L> labels, T... args);
    }
}
