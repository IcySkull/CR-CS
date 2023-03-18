package grafos.function;

import java.util.Collection;
import java.util.concurrent.atomic.AtomicReference;

import grafos.edges.AbstractEdge;

public class TraverseState <V, E extends AbstractEdge<V>> {
    public final AtomicReference<Collection<V>> frontierRef;
    public final AtomicReference<Collection<V>> traversalRef;
    public final AtomicReference<Collection<V>> visitedRef;

    public TraverseState(
        Collection<V> frontier,
        Collection<V> traversal, 
        Collection<V> visited) 
    {
        this.frontierRef = new AtomicReference<>(frontier);
        this.traversalRef = new AtomicReference<>(traversal);
        this.visitedRef = new AtomicReference<>(visited);
    }

    public void addFrontier(V v) {
        frontierRef.get().add(v);
    }

    public void addTraversal(V v) {
        traversalRef.get().add(v);
    }

    public void addVisited(V v) {
        visitedRef.get().add(v);
    }

    public Collection<V> getFrontier() {
        return frontierRef.get();
    }

    public Collection<V> getTraversal() {
        return traversalRef.get();
    }

    public Collection<V> getVisited() {
        return visitedRef.get();
    }

    public class Upcoming {
        public final TraverseState<V, E> state;
        public final V from;
        public final E adjEdge;
        public final V to;

        public Upcoming(
                TraverseState<V, E> state,
                V from,
                E adjEdge
        ) {
            this.state = state;
            this.from = from;
            this.adjEdge = adjEdge;
            this.to = adjEdge.adj(from);
        }

        public void addFrontier() {
            state.addFrontier(to);
        }

        public void addTraversal() {
            state.addTraversal(to);
        }

        public void addVisited() {
            state.addVisited(to);
        }

        public Collection<V> getFrontier() {
            return state.getFrontier();
        }

        public Collection<V> getTraversal() {
            return state.getTraversal();
        }

        public Collection<V> getVisited() {
            return state.getVisited();
        }
    }
}
