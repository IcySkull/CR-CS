package graph;

import java.util.AbstractMap;
import java.util.Collection;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Function;


/*
 * Functional interface for search algorithms. It is a function that takes 
 * {@code SearchState} and {@code SearchUtils} as parameters and returns a {@code SearchResult}.
 * It is used to implement search algorithms such as BFS, DFS, Dijkstra, etc. with the 
 * archetypal {@code searchPath} function in {@code AbstractGraph}. 
 */
@FunctionalInterface
public interface SearchFunction<V, E extends Edge<V>, L> {
    public SearchResult<V, L> search(SearchState<V, E, L> state, SearchUtils<V, E,L> utils);

    public class SearchResult<V, L> {
        public final V vertex;
        public final V adjacentVertex;
        public final AbstractMap.SimpleEntry<V, V> parentEntry;
        public final AbstractMap.SimpleEntry<V, L> labelEntry;
        public Boolean found;
        
        public SearchResult(V vertex, V adjacentVertex, AbstractMap.SimpleEntry<V, V> parentEntry, AbstractMap.SimpleEntry<V, L> labelEntry, Boolean found) {
            this.vertex = vertex;
            this.adjacentVertex = adjacentVertex;
            this.parentEntry = parentEntry;
            this.labelEntry = labelEntry;
            this.found = found;
        }
    }

    public class SearchState<V, E extends Edge<V>, L> {
        public final V v;
        public final E adjE;
        public final Collection<V> frontier;
        public final Map<V, V> parent;
        public final Map<V, L> labels;

        public SearchState(V v, E adjE, Collection<V> frontier, Map<V, V> parent, Map<V, L> labels) {
            this.v = v;
            this.adjE = adjE;
            this.frontier = frontier;
            this.parent = parent;
            this.labels = labels;
        }
    }

    public class SearchUtils<V, E extends Edge<V>, L> {
        public final Function<V, L> labeler;
        public final BiFunction<L, L, Boolean> labelerCmp;
        public final BinaryOperator<L> labelerAcc;
        public final Function<V, L> heuristic;
        public final TriConsumer<AbstractGraph<V, E>, V, V> onVisit;

        public SearchUtils() {
            this.labeler = v1 -> null;
            this.labelerCmp = (l1, l2) -> false;
            this.labelerAcc = (l1, l2) -> null;
            this.heuristic = v -> null;
            this.onVisit = (g, v1, v2) -> {};
        }

        public SearchUtils(
            Function<V, L> labeler, 
            BiFunction<L, L, Boolean> labelerCmp, 
            BinaryOperator<L> labelerAcc, 
            Function<V, L> heuristic,
            TriConsumer<AbstractGraph<V, E>, V, V> onVisit
        ) {
            this.labeler = labeler;
            this.labelerCmp = labelerCmp;
            this.labelerAcc = labelerAcc;
            this.heuristic = heuristic;
            this.onVisit = onVisit;
        }
    }
}
